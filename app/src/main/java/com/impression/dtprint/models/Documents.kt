package com.impression.dtprint.models

class Documents : Produits {

    var format: DocFormat = DocFormat.A4
    var doubleFaces: Boolean = false

    constructor(id: Long, nom: String, prix: Float, format: DocFormat = DocFormat.A4, doubleFaces: Boolean = false)
            : super(id, nom, prix) {
        this.format = format
        this.doubleFaces = doubleFaces
    }

    override fun toString(): String {
        return super.toString()
    }

    enum class DocFormat {
        A0, A1, A2, A3, A4, A5
    }


}

object ListDocuments {
    val list = listOf<Documents>(
        Documents(0,"Doc Format A0", 5f, Documents.DocFormat.A0),
        Documents(1,"Doc Format A1", 3f, Documents.DocFormat.A1),
        Documents(2,"Doc Format A2", 2f, Documents.DocFormat.A2),
        Documents(3,"Doc Format A3", 1f, Documents.DocFormat.A3),
        Documents(4,"Doc Format A4", 0.5f, Documents.DocFormat.A4),
        Documents(5,"Doc Format A5", 0.5f, Documents.DocFormat.A5)
    )
}




