package com.impression.dtprint.dao

import android.content.Context
import android.widget.Toast
import com.impression.dtprint.models.Goodies
import com.impression.dtprint.models.ListGoodies

object GoodiesController {

        private val db = ConnectionDB.db
        private val collection = db.collection("Produits")

        fun addGoodies(){
            ListGoodies.list.forEach{
                collection.document(""+ it.id).set(it)
            }
        }

        fun getAll(context: Context,firestoreCallback: FirestoreCallback ){
            var list: List<Goodies> = emptyList()
            collection.get()
                .addOnSuccessListener {
                   list = it.toObjects(Goodies::class.java)
                    firestoreCallback.onCallback(list)
                    Toast.makeText(context,"Goodies Loaded", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    print("Loading Error !")
                    print(it.message)
                }

        }

    public interface FirestoreCallback{
        fun onCallback(list:List<Goodies>)
    }

}
