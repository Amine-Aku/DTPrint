package com.impression.dtprint.models

import com.google.type.Date

class Commande<T> {
    var client: Client? = null
    var item: Item<T>? = null
    var dateCommande: Date? = null
    var dateLivraison: Date? = null
    var adresseLivraison: String? = null
    var prixTotal: Float? = null

    init {
        require(prixTotal!! > 0)
        dateCommande = Date.getDefaultInstance()
    }
}
