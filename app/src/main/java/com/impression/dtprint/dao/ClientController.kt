package com.impression.dtprint.dao
import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.impression.dtprint.models.Client
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.exceptions.RealmException
import java.lang.Exception

object ClientController{

    private val  db = ConnectionDB.db
    private val collection = db.collection("Client")

    fun addClient(context: Context, client: Client){
        collection.document().set(client)
            .addOnSuccessListener {
                Toast.makeText(context, "Client Added", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                println("Error !")
                println(it.message)
                Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show()
            }

    }

    fun getAll(){

    }




    }

