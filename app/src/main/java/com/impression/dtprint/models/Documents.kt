package com.impression.dtprint.models

import models.User

public class Documents : Produits {

    var format: String = DocFormat.A4.toString()
    var doubleFaces: Boolean = false

    constructor():super(){}

     constructor(id: Long, nom: String, prix: Float, type:String, format: String = DocFormat.A4.toString(), doubleFaces: Boolean = false)
            : super(id, nom, prix, type) {
        this.format = format
        this.doubleFaces = doubleFaces
    }





    enum class DocFormat {
        A0, A1, A2, A3, A4, A5
    }

    override fun toString(): String {
        return "Documents( ${super.toString()} format='$format', doubleFaces=$doubleFaces) "
    }


}

object ListDocuments {
    val list = listOf<Documents>(
        Documents(10,"Doc Format A0", 5f, Produits.Type.Document.toString(),Documents.DocFormat.A0.toString()),
        Documents(11,"Doc Format A1", 3f, Produits.Type.Document.toString(), Documents.DocFormat.A1.toString()),
        Documents(12,"Doc Format A2", 2f, Produits.Type.Document.toString(), Documents.DocFormat.A2.toString()),
        Documents(13,"Doc Format A3", 1f, Produits.Type.Document.toString(), Documents.DocFormat.A3.toString()),
        Documents(14,"Doc Format A4", 0.5f, Produits.Type.Document.toString(), Documents.DocFormat.A4.toString()),
        Documents(15,"Doc Format A5", 0.5f, Produits.Type.Document.toString(), Documents.DocFormat.A5.toString())
    )
}




