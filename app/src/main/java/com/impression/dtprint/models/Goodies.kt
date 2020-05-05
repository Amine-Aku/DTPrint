package com.impression.dtprint.models


public class Goodies : Produits {
    var goodieType: String? = null
    var stock: Int = 0
    var description: String = ""

    init {
        require(stock >= 0)
    }
    constructor():super(){}

    constructor(id: Long, nom: String, prix: Float, type: String, goodieType: String, stock: Int = 0, description: String = "")
            : super(id, nom, prix, type)  {
        this.goodieType = goodieType
        this.stock = stock
        this.description = description
    }

    enum class GoodiesType {
        T_Shirt,
        Stylo,
        Rollup,
        Banderole,
        Mug,
        Stickers
    }
}

object ListGoodies {
    val list = listOf<Goodies>(
        Goodies(20,"T Shirt", 50f, Produits.Type.Goodie.toString(), Goodies.GoodiesType.T_Shirt.toString()),
        Goodies(21,"Mug", 20f, Produits.Type.Goodie.toString(), Goodies.GoodiesType.Mug.toString()),
        Goodies(22,"Stylo", 10f, Produits.Type.Goodie.toString(), Goodies.GoodiesType.Stylo.toString()),
        Goodies(23,"Rollup", 100f, Produits.Type.Goodie.toString(), Goodies.GoodiesType.Rollup.toString()),
        Goodies(24,"Bandrole", 150f, Produits.Type.Goodie.toString(), Goodies.GoodiesType.Banderole.toString()),
        Goodies(25,"Stickers", 5f, Produits.Type.Goodie.toString(), Goodies.GoodiesType.Stickers.toString())
    )
}




