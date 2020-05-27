package com.impression.dtprint.models

import android.os.Parcel
import android.os.Parcelable
import com.google.type.Date

class Commande() : Parcelable {
    var numCommande: String? = null
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

    constructor(parcel: Parcel) : this() {
        qtt = parcel.readValue(Int::class.java.classLoader) as? Int
        dateCommande = parcel.readString()
        dateLivraison = parcel.readString()
        adresseLivraison = parcel.readString()
        prixTotal = parcel.readValue(Float::class.java.classLoader) as? Float
        url = parcel.readString()
        note = parcel.readString()
        prepared = parcel.readByte() != 0.toByte()
        delivred = parcel.readByte() != 0.toByte()
    }


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
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(qtt)
        parcel.writeString(dateCommande)
        parcel.writeString(dateLivraison)
        parcel.writeString(adresseLivraison)
        parcel.writeValue(prixTotal)
        parcel.writeString(url)
        parcel.writeString(note)
        parcel.writeByte(if (prepared) 1 else 0)
        parcel.writeByte(if (delivred) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Commande> {
        override fun createFromParcel(parcel: Parcel): Commande {
            return Commande(parcel)
        }

        override fun newArray(size: Int): Array<Commande?> {
            return arrayOfNulls(size)
        }
    }


}

