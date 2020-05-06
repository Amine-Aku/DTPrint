package com.impression.dtprint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.impression.dtprint.Adapters.ProduitsAdapter
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.Produits

class TestActivity : AppCompatActivity() {


    val collection = ConnectionDB.db.collection("Produits")
    var adapter: ProduitsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        val query = collection.orderBy("id")
        val options = FirestoreRecyclerOptions.Builder<Produits>()
            .setQuery(query, Produits::class.java)
            .setLifecycleOwner(this)
            .build()

        adapter = ProduitsAdapter(options)
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_test)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}

