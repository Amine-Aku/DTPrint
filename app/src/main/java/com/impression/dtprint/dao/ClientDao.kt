package com.impression.dtprint.dao
import android.widget.Toast
import com.impression.dtprint.models.Client
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.exceptions.RealmException
import java.lang.Exception

open  class ClientDao{

    object Instance{ val _instace: ClientDao = ClientDao()}

    val config = RealmConfiguration.Builder().name("Client.realm").build()
    val realm = Realm.getInstance(config)

    fun addClient(client: Client){
            realm.beginTransaction()
        try {
            var nextID: Long = realm.where(Client::class.java).count()+1
            var cl = realm.createObject(Client::class.java, nextID)
            cl.nom = client.nom
            cl.prenom = client.prenom
            cl.userName = client.userName
            cl.email = client.email
            cl.password = client.password
            cl.ville = client.ville
            cl.adresse = client.adresse

            realm.commitTransaction()
        }catch (e: RealmException){
            e.message
        }
    }

    fun getAll(): List<Client>{
        val realmResult = realm.where(Client::class.java).findAll()
        val list = realmResult

        return list
    }




    }

