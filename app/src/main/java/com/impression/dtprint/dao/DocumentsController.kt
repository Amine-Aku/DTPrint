package com.impression.dtprint.dao

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.impression.dtprint.models.Documents
import com.impression.dtprint.models.ListDocuments
import com.impression.dtprint.models.Produits


object DocumentsController {


        private val db = ConnectionDB.db
        private var collection = db.collection("Produits")

        fun addDocuments(){
            ListDocuments.list.forEach{
                var id = it.id
                collection.document(""+id).set(it)
            }
        }



        fun getDocument(context: Context, id:Long):String{
            var text:String = "Doc : "
            db.collection("Documents").document(""+id).get()
               .addOnSuccessListener {
                   val doc = it.toObject(Documents::class.java)
                   text += doc!!.toString()
//                   Toast.makeText(context,"Doc "+ text, Toast.LENGTH_SHORT).show()

               }
            Toast.makeText(context,"Doc "+ text, Toast.LENGTH_SHORT).show()
                return  text
        }

    public interface FirestoreCallback{
                fun onCallback(list:List<Produits>)
    }

}
