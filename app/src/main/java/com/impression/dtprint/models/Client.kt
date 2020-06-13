package com.impression.dtprint.models

import android.os.Parcel
import android.os.Parcelable


open class Client() : Parcelable {

    var username:String ?= ""
    var password:String ?=""
    var type: String? = ""


    var nom: String? =""
    var prenom: String? =""
    var ville: String? = ""
    var adresse:String? =""
    var email: String? = ""
    var numTel: String? = ""

    constructor(parcel: Parcel) : this() {
        username = parcel.readString()
        password = parcel.readString()
        type = parcel.readString()
        nom = parcel.readString()
        prenom = parcel.readString()
        ville = parcel.readString()
        adresse = parcel.readString()
        email = parcel.readString()
        numTel = parcel.readString()
    }


    constructor(username: String ,password: String, nom: String, prenom: String, ville: String, adresse: String,
                email: String, numTel: String, type: String? = UserType.Client.toString())
    :this(){
        this.username = username
        this.password = password
        this.type = type
        this.nom=nom
        this.prenom=prenom
        this.ville=ville
        this.adresse=adresse
        this.email=email
        this.numTel = numTel
    }

    enum class UserType {
        Client,Agent,Livreur
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(type)
        parcel.writeString(nom)
        parcel.writeString(prenom)
        parcel.writeString(ville)
        parcel.writeString(adresse)
        parcel.writeString(email)
        parcel.writeString(numTel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Client> {
        override fun createFromParcel(parcel: Parcel): Client {
            return Client(parcel)
        }

        override fun newArray(size: Int): Array<Client?> {
            return arrayOfNulls(size)
        }
    }

}
