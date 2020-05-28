package com.impression.dtprint.models

import android.os.Parcel
import android.os.Parcelable
import com.google.type.Date

class Commande() : Parcelable {
    var numCommande: String? = null
    var client: Client? = null

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
    var delivered: Boolean = false
    var pageCount = 1

    constructor(parcel: Parcel) : this() {
        numCommande = parcel.readString()
        client = parcel.readParcelable(Client::class.java.classLoader)
        qtt = parcel.readValue(Int::class.java.classLoader) as? Int
        dateCommande = parcel.readString()
        dateLivraison = parcel.readString()
        adresseLivraison = parcel.readString()
        prixTotal = parcel.readValue(Float::class.java.classLoader) as? Float
        url = parcel.readString()
        note = parcel.readString()
        prepared = parcel.readByte() != 0.toByte()
        delivered = parcel.readByte() != 0.toByte()
        pageCount = parcel.readInt()
    }


    constructor(client: Client?, qtt: Int?, adresseLivraison: String?, prixTotal: Float?, url: String?, note: String?):this() {
        this.client = client
        this.qtt = qtt
        this.adresseLivraison = adresseLivraison
        this.prixTotal = prixTotal
        this.url = url
        this.note = note
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(numCommande)
        parcel.writeParcelable(client, flags)
        parcel.writeValue(qtt)
        parcel.writeString(dateCommande)
        parcel.writeString(dateLivraison)
        parcel.writeString(adresseLivraison)
        parcel.writeValue(prixTotal)
        parcel.writeString(url)
        parcel.writeString(note)
        parcel.writeByte(if (prepared) 1 else 0)
        parcel.writeByte(if (delivered) 1 else 0)
        parcel.writeInt(pageCount)
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

