package com.impression.dtprint.models

open class Produits() {

    var id:Long
    var nom: String? = null
    var prix: Float = 0f
    var type: String? = null

    init {
        id = 0
        require(prix>=0)
    }

    constructor(id: Long) : this(){
        this.id = id
    }

    constructor(id:Long = 0, nom:String = "", prix:Float = 0f,type:String): this(){
        this.id = id
        this.nom = nom
        this.prix = prix
        this.type = type
    }

    override fun toString(): String {
        return "Produits(id=$id, nom='$nom', prix=$prix, type='$type')"
    }

    enum class Type{
        Document,Goodie
    }



}

object ListProduits{
    var list: List<Produits> = ListDocuments.list + ListGoodies.list
}

