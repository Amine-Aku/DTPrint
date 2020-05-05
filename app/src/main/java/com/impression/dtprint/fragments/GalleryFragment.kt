package com.impression.dtprint.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.impression.dtprint.Adapters.ProduitsAdapter
import com.impression.dtprint.MainActivity
import com.impression.dtprint.R
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.dao.DocumentsController
import com.impression.dtprint.dao.GoodiesController
import com.impression.dtprint.models.Documents
import com.impression.dtprint.models.Goodies
import com.impression.dtprint.models.ListProduits
import com.impression.dtprint.models.Produits
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import com.impression.dtprint.Adapters.GalleryAdapter as GalleryAdapter

class GalleryFragment() : Fragment(){
    val db = FirebaseFirestore.getInstance()
    val collection = db.collection("Produits")
    var adapter: ProduitsAdapter? = null
    var Gview:View? = null
    var list:List<Produits>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Gview = inflater.inflate(R.layout.fragment_gallery, container, false)

         var recyclerView: RecyclerView = Gview!!.findViewById(R.id.recycler_view_gallery)
         recyclerView.layoutManager = LinearLayoutManager(activity)
         var list = collection.get()
             .addOnSuccessListener {
                 list = it.toObjects(Produits::class.java)
                 recyclerView.adapter = GalleryAdapter(activity!!, list!!)
             }

//        DocumentsController.getAll(activity!!, object:DocumentsController.FirestoreCallback{
//            override fun onCallback(list: List<Produits>) {
//                recyclerView.adapter = GalleryAdapter(activity!!, list)
//            }
//        })
        setUpRecyclerView(Gview!!)
        return Gview
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }


    private fun setUpRecyclerView(view: View) {
        val query = collection.orderBy("id")
        val options =FirestoreRecyclerOptions.Builder<Produits>()
            .setQuery(query, Produits::class.java)
            .setLifecycleOwner(this)
            .build()

        adapter = ProduitsAdapter(options)
        var recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_gallery)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
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

