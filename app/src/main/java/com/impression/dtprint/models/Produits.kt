package com.impression.dtprint.models

open class Produits() {

    var id:Long
    var nom: String = ""
    var prix: Float = 0f

    init {
        id = 0
        require(prix>=0)
    }

    constructor(id: Long) : this(){
        this.id = id
    }

    constructor(id:Long = 0, nom:String = "", prix:Float = 0f): this(){
        this.nom = nom
        this.prix = prix
    }

}

object ListProduits{
    var list: List<Produits> = ListDocuments.list + ListGoodies._list
}

