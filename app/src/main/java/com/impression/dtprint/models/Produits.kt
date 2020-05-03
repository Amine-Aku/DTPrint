package com.impression.dtprint.models

public open class Produits() {

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
        this.id = id
        this.nom = nom
        this.prix = prix
    }

    override fun toString(): String {
        return "Produits(id=$id, nom='$nom', prix=$prix)"
    }


}

object ListProduits{
    var list: List<Produits> = ListDocuments.list + ListGoodies.list
}

