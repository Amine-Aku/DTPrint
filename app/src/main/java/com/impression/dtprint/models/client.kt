package com.impression.dtprint.models

import models.user

class client: user {

    var nom: String ="zineb"
    var prenom: String ="chacrone"
    var ville : String= "marrakech"
    var adresse :String ="ensa"
    var email : String = "test@gmail.com"
    var numCarteBancaire:Long ?=123456
        get() {
            println("geting card")
            return field
        }
        set(value) {
            println("seting card")
            field=value
        }

    init {
        println("new client is here $id")
    }
    constructor(name:String,name2:String,city:String,adr:String,Email:String):super(){
        this.nom=name
        this.prenom=name2
        this.ville=city
        this.adresse=adr
        this.email=Email
    }
    constructor(Id:Int,name:String,name2:String,city:String,adr:String,Email:String):super(Id){
        this.nom=name
        this.prenom=name2
        this.ville=city
        this.adresse=adr
        this.email=Email


    }

    constructor(Id: Int, name: String, userPassword: String, nom: String, prenom: String, ville: String, adresse: String, email: String) : super(Id, name, userPassword) {
        this.nom = nom
        this.prenom = prenom
        this.ville = ville
        this.adresse = adresse
        this.email = email
    }

    constructor() : super(){
        println("client est créé")
    }
}
