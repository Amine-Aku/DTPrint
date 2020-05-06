package com.impression.dtprint.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.impression.dtprint.R
import com.impression.dtprint.TestActivity
import com.impression.dtprint.WishlistActivity
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.dao.DocumentsController
import com.impression.dtprint.models.Documents
import com.impression.dtprint.models.Produits

class HomeFragment : Fragment() {

    var text_area:TextView? = null

    val db = ConnectionDB.db
    val collection = db.collection("Documents")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val btn = view.findViewById<Button>(R.id.recycler_btn)
        btn.setOnClickListener {
            startActivity(Intent(context, TestActivity::class.java))
        }
//        val btn2 = view.findViewById<Button>(R.id.recycler_btn2)
//        btn2.setOnClickListener {
//            startActivity(Intent(context, WishlistActivity::class.java))
//        }


        return view
    }

}
