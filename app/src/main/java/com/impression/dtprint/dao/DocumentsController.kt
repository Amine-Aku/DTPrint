package com.impression.dtprint.dao

import android.content.Context
import android.widget.Toast
import com.impression.dtprint.models.Documents
import com.impression.dtprint.models.ListDocuments


object DocumentsController {

        private val db = ConnectionDB.db
        private var collection = db.collection("Documents")

        fun addDocuments(){
            ListDocuments.list.forEach{
                var id = it.id
                collection.document(""+id).set(it)
            }
        }

        fun getAll(context: Context, firestoreCallback: FirestoreCallback){
            var list: List<Documents> = emptyList()
//            var text:String = "list : \n"
            print("TEST 0")
            collection.get()
                .addOnSuccessListener {
                    list = it.toObjects(Documents::class.java)
                    firestoreCallback.onCallback(list)
                    Toast.makeText(context,"Doc Loaded", Toast.LENGTH_SHORT).show()
                  }
                .addOnFailureListener {
                    print("Loading Error !")
                    Toast.makeText(context,"Error !", Toast.LENGTH_SHORT).show()
                    print(it.message)
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
                fun onCallback(list:List<Documents>)
    }

}
