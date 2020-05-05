package com.impression.dtprint.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import models.User.UserType


open class Client(){


    var username:String ?= ""
    var password:String ?=""
    var type: String? = ""


    var nom: String? =""
    var prenom: String? =""
    var ville: String? = ""
    var adresse:String? =""
    var email: String? = ""
    var numCarteBancaire: Long? = null



    constructor(username: String ,password: String, nom: String, prenom: String, ville: String, adresse: String,
                email: String, type: String? = UserType.Client.toString(), RIB: Long? = null)
    :this(){
        this.username = username
        this.password = password
        this.type = type
        this.nom=nom
        this.prenom=prenom
        this.ville=ville
        this.adresse=adresse
        this.email=email
        this.numCarteBancaire = RIB
    }



}
