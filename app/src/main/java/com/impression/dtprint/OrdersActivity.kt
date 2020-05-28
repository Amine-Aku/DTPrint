package com.impression.dtprint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.impression.dtprint.Adapters.OrdersAdapter
import com.impression.dtprint.agent.OrdersAgentAdapter
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.Client
import com.impression.dtprint.models.Commande
import com.impression.dtprint.models.CurrentClient

class OrdersActivity : AppCompatActivity() {

//    val collection = ConnectionDB.db.collection("Commandes")


    val db = FirebaseFirestore.getInstance()
    val collection = db.collection("Commandes")

    var intentUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_orders)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        intent?.let{
            intentUser = it.extras!!.getString("user")
        }

        if( intentUser == Client.UserType.Client.toString() ){
            title = resources.getString((R.string.your_orders))
            recyclerView.adapter = setUpRecyclerView()
        }
        else if(intentUser == Client.UserType.Agent.toString()){
            title = resources.getString((R.string.cardView_btn_order))
            recyclerView.adapter = setUpRecyclerViewAgent()
        }



    }
    private fun setUpRecyclerView(): OrdersAdapter{
        val query = collection
            .whereEqualTo("client", CurrentClient.user)
            .orderBy("delivered")
            .orderBy("prepared")
            .orderBy("numCommande", Query.Direction.DESCENDING)

        val options = FirestoreRecyclerOptions.Builder<Commande>()
            .setQuery(query, Commande::class.java)
            .setLifecycleOwner(this)
            .build()

        var adapter = OrdersAdapter(options)

        adapter!!.setOnItemClickListener(object: OrdersAdapter.OnItemClickListener{
            override fun onDetailClick(documentSnapshot: DocumentSnapshot, position: Int) {
                val intent = Intent(this@OrdersActivity, OrderDetailActivity::class.java)
                val order = documentSnapshot.toObject(Commande::class.java)
                intent.putExtra("order", order)
                intent.putExtra("client", order!!.client)
                if(order!!.document != null){
                    intent.putExtra("prodId", order.document!!.id.toString())
                }
                else if(order!!.goodie != null){
                    intent.putExtra("prodId", order.goodie!!.id.toString())
                }
                startActivity(intent)
            }
        })

        return adapter!!
    }

    private fun setUpRecyclerViewAgent(): OrdersAgentAdapter {
        val query = collection
            .orderBy("delivered")
            .orderBy("prepared")
            .orderBy("numCommande", Query.Direction.DESCENDING)

        val options = FirestoreRecyclerOptions.Builder<Commande>()
            .setQuery(query, Commande::class.java)
            .setLifecycleOwner(this)
            .build()

        var adapter = OrdersAgentAdapter(options)

        adapter!!.setOnItemClickListener(object: OrdersAgentAdapter.OnItemClickListener{
            override fun onDetailClick(documentSnapshot: DocumentSnapshot, position: Int) {
                val intent = Intent(this@OrdersActivity, OrderDetailActivity::class.java)
                val order = documentSnapshot.toObject(Commande::class.java)
                intent.putExtra("order", order)
                intent.putExtra("client", order!!.client)
                if(order!!.document != null){
                    intent.putExtra("prodId", order.document!!.id.toString())
                }
                else if(order!!.goodie != null){
                    intent.putExtra("prodId", order.goodie!!.id.toString())
                }
                startActivity(intent)
            }

            override fun onValidate(documentSnapshot: DocumentSnapshot, position: Int, isChecked: Boolean, pageCount: Int ) {
                collection.document(documentSnapshot.id).update("prepared", isChecked, "pageCount", pageCount)
                    .addOnSuccessListener {
                        Toast.makeText(this@OrdersActivity, "Order Validated", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@OrdersActivity, "Validation Error", Toast.LENGTH_SHORT).show()
                    }
            }
        })

        return adapter!!
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

//    override fun onStart() {
//        super.onStart()
//        adapter!!.startListening()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        adapter!!.stopListening()
//    }

}
