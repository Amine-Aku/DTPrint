package com.impression.dtprint.models


public class Goodies : Produits {
    var type: String? = null
    var stock: Int = 0
    var description: String = ""

    init {
        require(stock >= 0)
    }
    constructor():super(){}

    constructor(id: Long, nom: String, prix: Float, type: String, stock: Int = 0, description: String = "")
            : super(id, nom, prix)  {
        this.type = type
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
        Goodies(10,"T Shirt", 50f, Goodies.GoodiesType.T_Shirt.toString()),
        Goodies(11,"Mug", 20f, Goodies.GoodiesType.Mug.toString()),
        Goodies(12,"Stylo", 10f, Goodies.GoodiesType.Stylo.toString()),
        Goodies(13,"Rollup", 100f, Goodies.GoodiesType.Rollup.toString()),
        Goodies(14,"Bandrole", 150f, Goodies.GoodiesType.Banderole.toString()),
        Goodies(15,"Stickers", 5f, Goodies.GoodiesType.Stickers.toString())
    )
}




