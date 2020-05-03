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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)


         var recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_gallery)
         recyclerView.layoutManager = LinearLayoutManager(activity)

//        var list = DocumentsController.getAll(this.activity!!)
        DocumentsController.getAll(activity!!, object:DocumentsController.FirestoreCallback{
            override fun onCallback(list: List<Documents>) {
//                recyclerView.adapter = GalleryAdapter(activity!!, list)

                GoodiesController.getAll(activity!!, object:GoodiesController.FirestoreCallback{
                    override fun onCallback(list2: List<Goodies>) {
                        recyclerView.adapter = GalleryAdapter(activity!!, list+list2)
                    }
                })


            }
        })


        return view
    }
}
