package com.impression.dtprint.fragments

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
import com.impression.dtprint.MainActivity
import com.impression.dtprint.R
import com.impression.dtprint.models.ListProduits
import com.impression.dtprint.models.Produits
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import com.impression.dtprint.Adapters.GalleryAdapter as GalleryAdapter

class GalleryFragment() : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
/*
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view_gallery.layoutManager = layoutManager

        val adapter = GalleryAdapter(activity!!, ListProduits.list)
        recycler_view_gallery.adapter = adapter
 */

         var recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_gallery)
         recyclerView.layoutManager = LinearLayoutManager(activity)


        recyclerView.adapter = GalleryAdapter(this.activity!!, ListProduits.list)



        return view
    }
}
