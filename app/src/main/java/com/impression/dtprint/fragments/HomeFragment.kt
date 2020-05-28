package com.impression.dtprint.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.impression.dtprint.Adapters.DocumentsAdapter
import com.impression.dtprint.Adapters.GalleryAdapter
import com.impression.dtprint.LoginActivity
import com.impression.dtprint.OrderActivity
import com.impression.dtprint.OrdersActivity
import com.impression.dtprint.R
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.Client
import com.impression.dtprint.models.CurrentClient
import com.impression.dtprint.models.Documents
import com.impression.dtprint.models.Produits

class HomeFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 1
    var agentBtn: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)


        agentBtn = v.findViewById(R.id.goto_agent_orders)
        agentBtn!!.setOnClickListener {
            val intent = Intent(context, OrdersActivity::class.java)
            intent.putExtra("user", Client.UserType.Agent.toString())
            startActivity(intent)
        }


//        val btn2 = view.findViewById<Button>(R.id.recycler_btn2)
//        btn2.setOnClickListener {
//
//                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//                intent.setType("application/pdf")
//                startActivityForResult(intent, 365)
//
//        }


        return v
    }

}
