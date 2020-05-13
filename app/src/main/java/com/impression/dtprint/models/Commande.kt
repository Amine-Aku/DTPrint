package com.impression.dtprint.models

import com.google.type.Date

class Commande() {
    var client: Client? = null
//    var item: Item<T>? = null

    var document: Documents? = null
    var goodie: Goodies? = null
    var qtt: Int? = null

    var dateCommande: String? = null
    var dateLivraison: String? = null
    var adresseLivraison: String? = null
    var prixTotal: Float? = null

    var url: String? = null
    var note: String? = null
    var prepared: Boolean = false
    var delivred: Boolean = false


    constructor(client: Client?, qtt: Int?, adresseLivraison: String?, prixTotal: Float?, url: String?, note: String?):this() {
        this.client = client
        this.qtt = qtt
        this.adresseLivraison = adresseLivraison
        this.prixTotal = prixTotal
        this.url = url
        this.note = note
    }




//    init {
//        require(prixTotal!! > 0)
//    }


}

