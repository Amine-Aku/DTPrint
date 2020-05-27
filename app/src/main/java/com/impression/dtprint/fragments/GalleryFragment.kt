package com.impression.dtprint.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.impression.dtprint.Adapters.GalleryAdapter
import com.impression.dtprint.Adapters.ProduitsAdapter
import com.impression.dtprint.LoginActivity
import com.impression.dtprint.MainActivity
import com.impression.dtprint.OrderActivity
import com.impression.dtprint.R
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.CurrentClient
import com.impression.dtprint.models.Produits

class GalleryFragment() : Fragment(){
    val db = FirebaseFirestore.getInstance()
    val collection = db.collection("Produits")
    var collectionWishlist: CollectionReference? = null
    var adapter: ProduitsAdapter? = null
    var adapterG: GalleryAdapter? = null
    var Gview:View? = null
    var list:List<Produits>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Gview = inflater.inflate(R.layout.fragment_gallery, container, false)

        if(CurrentClient.loggedIn){
            collectionWishlist = ConnectionDB.db.collection("Client")
                .document(CurrentClient.id!!).collection("Wishlist")
        }



         var recyclerView: RecyclerView = Gview!!.findViewById(R.id.recycler_view_gallery)
         recyclerView.layoutManager = LinearLayoutManager(activity)
         var list = collection.get()
             .addOnSuccessListener {
                 list = it.toObjects(Produits::class.java)
                 adapterG = GalleryAdapter(activity!!, list!!)
                 recyclerView.adapter = adapterG

                 // Item View Btn Events Configuration
                 adapterG!!.setOnItemClickListener(object: GalleryAdapter.OnItemClickListener{
                     override fun onOrderClick(l: List<Produits>, position: Int) {
                         var intent:Intent? = null
                         CurrentClient.aboutToOrder = true
                         if(!CurrentClient.loggedIn){
                             intent = Intent(activity!!, LoginActivity::class.java)
                         }
                         else{
                             intent = Intent(activity!!, OrderActivity::class.java)
                         }
                         intent!!.putExtra("ProdId", ""+l[position].id)
                         startActivity(intent)
                     }

                     override fun onWishlistClick(l: List<Produits>, position: Int) {
                         if(CurrentClient.loggedIn){
                             collectionWishlist!!.document(l[position].nom!!).set(l[position])
                                 .addOnSuccessListener {
                                     Toast.makeText(activity!!, "Added to your Wish list", Toast.LENGTH_SHORT).show()
                                 }
                                 .addOnFailureListener {
                                     Toast.makeText(activity!!, "wishlist error", Toast.LENGTH_SHORT).show()

                                 }
                         }
                         else{
                             Toast.makeText(activity!!, "You must Sign In", Toast.LENGTH_SHORT).show()
                         }

                     }

                     override fun onMoreClick(l: List<Produits>, position: Int) {

                     }
                 })
             }


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


        //


    }




}

