package com.impression.dtprint.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.impression.dtprint.R
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.dao.DocumentsController
import com.impression.dtprint.models.Documents

class HomeFragment : Fragment() {

    var text_area:TextView? = null

    val db = ConnectionDB.db
    val collection = db.collection("Documents")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        text_area = view.findViewById<TextView>(R.id.home_area)

//        DocumentsController.getAll(activity!!, object:DocumentsController.FirestoreCallback{
//            override fun onCallback(text: String) {
//                text_area!!.text = text
//                Toast.makeText(context,"Doc Loaded"  , Toast.LENGTH_SHORT).show()
//            }
//        })






        return view
    }

//    fun getAll(){
//        var list: List<Documents> = emptyList()
//        var text:String = "list : \n"
//        print("TEST 0")
//        collection.get()
//            .addOnSuccessListener {
//
//                print("TEST 1")
//                it.forEach {
//                    val q: Documents = it.toObject(Documents::class.java)
////                        var newDoc = Documents(q.id, q.nom, q.prix, q.format, q.doubleFaces)
////                        list += ListDocuments.list.get(0)
//                    text += "DOC : "+"\n"
//                    print("TEST 2")
//                    Toast.makeText(context,"Doc Loaded", Toast.LENGTH_SHORT).show()
//
//                }
//                text_area!!.text = text
//
//            }
//            .addOnFailureListener {
//                print("Loading Error !")
//                Toast.makeText(context,"Error !", Toast.LENGTH_SHORT).show()
//                //print(it.message)
//            }
//
//    }
}
