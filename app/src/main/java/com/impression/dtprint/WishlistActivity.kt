package com.impression.dtprint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.impression.dtprint.Adapters.ProduitsAdapter
import com.impression.dtprint.Adapters.WishlistAdapter
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.CurrentClient
import com.impression.dtprint.models.Produits

class WishlistActivity : AppCompatActivity() {

    val collection = ConnectionDB.db.collection("Client")
        .document(CurrentClient.id!!).collection("Wishlist")

    var adapter: WishlistAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        title = resources.getString(R.string.your_wishlist)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val query = collection.orderBy("id")
        val options = FirestoreRecyclerOptions.Builder<Produits>()
            .setQuery(query, Produits::class.java)
            .setLifecycleOwner(this)
            .build()

        adapter = WishlistAdapter(options)
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_wishlist)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        ItemTouchHelper(
            object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adapter!!.deleteWishlistItem(viewHolder.adapterPosition)
                }
            }).attachToRecyclerView(recyclerView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
