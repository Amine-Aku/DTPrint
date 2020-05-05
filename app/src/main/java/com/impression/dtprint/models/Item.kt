package com.impression.dtprint.models

class Item<T>(var produits: T, var prix_u: Float, var qtt: Int) {

    init {
        require(qtt > 0)
    }
}
