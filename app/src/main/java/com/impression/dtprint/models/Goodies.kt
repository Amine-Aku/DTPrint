package com.impression.dtprint.models


class Goodies : Produits {
    var type: GoodiesType? = null
    var stock: Int = 0
    var description: String = ""

    init {
        require(stock >= 0)
    }


    constructor(id: Long, nom: String, prix: Float, type: GoodiesType, stock: Int = 0, description: String = "")
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
        Meugue,
        Stickers
    }
}

object ListGoodies {
    val _list = listOf<Goodies>(
        Goodies(10,"T Shirt", 50f, Goodies.GoodiesType.T_Shirt),
        Goodies(11,"Meugue", 20f, Goodies.GoodiesType.Meugue),
        Goodies(12,"Stylo", 10f, Goodies.GoodiesType.Stylo),
        Goodies(13,"Rollup", 100f, Goodies.GoodiesType.Rollup),
        Goodies(14,"Bandrole", 150f, Goodies.GoodiesType.Banderole),
        Goodies(15,"Stickers", 5f, Goodies.GoodiesType.Stickers)
    )
}




