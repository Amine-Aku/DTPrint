package com.impression.dtprint.models

public class Documents : Produits {

    var format: String = DocFormat.A4.toString()
    var doubleFaces: Boolean = false

    constructor():super(){}

     constructor(id: Long, nom: String, prix: Float, format: String = DocFormat.A4.toString(), doubleFaces: Boolean = false)
            : super(id, nom, prix) {
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
        Documents(0,"Doc Format A0", 5f, Documents.DocFormat.A0.toString()),
        Documents(1,"Doc Format A1", 3f, Documents.DocFormat.A1.toString()),
        Documents(2,"Doc Format A2", 2f, Documents.DocFormat.A2.toString()),
        Documents(3,"Doc Format A3", 1f, Documents.DocFormat.A3.toString()),
        Documents(4,"Doc Format A4", 0.5f, Documents.DocFormat.A4.toString()),
        Documents(5,"Doc Format A5", 0.5f, Documents.DocFormat.A5.toString())
    )
}




