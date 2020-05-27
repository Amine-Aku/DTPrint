package com.impression.dtprint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.impression.dtprint.Adapters.OrdersAdapter
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.Commande
import com.impression.dtprint.models.CurrentClient

class OrdersActivity : AppCompatActivity() {

//    val collection = ConnectionDB.db.collection("Commandes")
    var adapter: OrdersAdapter? = null

    val db = FirebaseFirestore.getInstance()
    val collection = db.collection("Commandes")

    val intentName = "order"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        title = resources.getString((R.string.your_orders))
        setUpRecyclerView()


    }
    private fun setUpRecyclerView() {
        val query = collection
            .whereEqualTo("client", CurrentClient.user)
            .orderBy("delivered")
            .orderBy("prepared")
            .orderBy("numCommande", Query.Direction.DESCENDING)

        val options = FirestoreRecyclerOptions.Builder<Commande>()
            .setQuery(query, Commande::class.java)
            .build()

        adapter = OrdersAdapter(options)
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_orders)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter!!.setOnItemClickListener(object: OrdersAdapter.OnItemClickListener{
            override fun onDetailClick(documentSnapshot: DocumentSnapshot, position: Int) {
                val intent = Intent(this@OrdersActivity, OrderDetailActivity::class.java)
                val order = documentSnapshot.toObject(Commande::class.java)
                intent.putExtra("order", order)
                if(order!!.document != null){
                    intent.putExtra("prodId", order.document!!.id.toString())
                }
                else if(order!!.goodie != null){
                    intent.putExtra("prodId", order.goodie!!.id.toString())
                }
                startActivity(intent)
            }



        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }

}
