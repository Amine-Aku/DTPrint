package com.impression.dtprint.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import models.User.UserType

@RealmClass
open class Client() : RealmObject(){

    @PrimaryKey
    var id: Long =0
    var userName:String ?= ""
    var password:String ?=""
    var type: String? = ""


    var nom: String? =""
    var prenom: String? =""
    var ville: String? = ""
    var adresse:String? =""
    var email: String? = ""
    var numCarteBancaire: Long? = null


    init {
        println("New Client is here $id / $nom")
    }

//    constructor(id: Long ,username: String ,password: String, type: UserType = UserType.Client,
//                nom: String, prenom: String, ville: String, adresse: String, email: String, RIB: Long? = null)
//    {
//        this.id = id
//        this.userName = userName
//        this.password = password
//        this.type = type
//        this.nom=nom
//        this.prenom=prenom
//        this.ville=ville
//        this.adresse=adresse
//        this.email=email
//    }

    constructor(username: String ,password: String, nom: String, prenom: String, ville: String, adresse: String,
                email: String, type: String? = UserType.Client.toString(), RIB: Long? = null)
    :this(){
        this.userName = userName
        this.password = password
        this.type = type
        this.nom=nom
        this.prenom=prenom
        this.ville=ville
        this.adresse=adresse
        this.email=email
    }

//    constructor(id: Long, name: String, userPassword: String, nom: String, prenom: String, ville: String, adresse: String, email: String) : super(id, name, userPassword) {
//        this.nom = nom
//        this.prenom = prenom
//        this.ville = ville
//        this.adresse = adresse
//        this.email = email
//    }

}
