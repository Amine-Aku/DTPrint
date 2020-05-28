package com.impression.dtprint

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.*
import java.io.File

class OrderDetailActivity : AppCompatActivity() {

    private var numCommandeField: TextView? = null
    private var clientField: TextView? = null

    private var prodImage: ImageView? = null
    private var prodNameField: TextView? = null
    private var priceField: TextView? = null
    private var formatField: TextView? = null
    private var doubleFaceField: TextView? = null
    private var goodieTypeField: TextView? = null

    private var numTelField: TextView? = null
    private var qttField: TextView? = null
    private var noteField: TextView? = null
    private var shippingAddressField: TextView? = null
    private var totalField: TextView? = null

    private var totalLabel: TextView? = null

    // VARs

    private var order: Commande? = null
    private var prodId: String? = null
    private var client: Client? = null

    val db = ConnectionDB.db
    val productCollection = db.collection("Produits")
    val storage = FirebaseStorage.getInstance()
    var uploadTask: StorageTask<UploadTask.TaskSnapshot>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        intent?.let{
            order = intent.extras!!.getParcelable<Commande>("order")
            client = intent.extras!!.getParcelable<Client>("client")

            prodId = intent.extras!!.getString("prodId")
        }


        prodImage = findViewById(R.id.order_detail_product_image)
        numCommandeField = findViewById(R.id.order_detail_view_numCommande)
        clientField = findViewById(R.id.order_detail_view_client)

        prodNameField = findViewById(R.id.order_detail_view_product_name)
        priceField = findViewById(R.id.order_detail_view_price)
        formatField = findViewById(R.id.order_detail_view_format)
        doubleFaceField = findViewById(R.id.order_detail_doubleFace)
        goodieTypeField = findViewById(R.id.order_detail_view_type)

        numTelField = findViewById(R.id.order_detail_view_tel)
        noteField = findViewById(R.id.order_detail_view_note)
        qttField = findViewById(R.id.order_detail_numPicker_qtt)
        shippingAddressField = findViewById(R.id.order_detail_view_shipping_address)
        totalField = findViewById(R.id.order_detail_view_total)


        totalLabel = findViewById(R.id.total_label_detail)


        if(prodId!![0] == '1'){
            productCollection.document(prodId!!).get()
                .addOnSuccessListener {
                    val doc = it.toObject(Documents::class.java)
                    prodNameField!!.text = doc!!.nom
                    priceField!!.text = doc!!.prix.toString()
                    formatField!!.text = doc!!.format
                    doubleFaceField!!.text = doc!!.doubleFaces.toString()

                    val layout: LinearLayout = findViewById(R.id.order_detail_goodie_details)
                    layout.visibility = View.GONE

                    fileDownload("")
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Goodie Error", Toast.LENGTH_LONG).show()
                }

        }
        else if(prodId!![0] == '2'){
            productCollection.document(prodId!!).get()
                .addOnSuccessListener {
                    val doc = it.toObject(Goodies::class.java)
                    prodNameField!!.text = doc!!.nom
                    priceField!!.text = doc!!.prix.toString()
                    goodieTypeField!!.text = doc!!.goodieType

                    val layout: LinearLayout = findViewById(R.id.order_detail_document_details)
                    layout.visibility = View.GONE

            fileDownload(doc!!.goodieType!!)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Goodie Error", Toast.LENGTH_LONG).show()
                }

        }

        numCommandeField!!.text = order!!.numCommande.toString()
        clientField!!.text = client!!.prenom +" "+ client!!.nom+" ("+ client!!.username+")"
        numTelField!!.text = client!!.numTel
        noteField!!.text = order!!.note
        qttField!!.text = order!!.qtt.toString()
        shippingAddressField!!.text = order!!.adresseLivraison
        totalField!!.text = order!!.prixTotal.toString()



    }


    private fun fileDownload(goodieType: String){
        val fileRef = storage.reference.child(order!!.url!!)
        var file = File.createTempFile("uploads","jpg")
        fileRef!!.getFile(file)
            .addOnSuccessListener {
                Toast.makeText(this, "File Loaded!", Toast.LENGTH_SHORT).show()
                val uri = Uri.fromFile(file)
                val extension =  order!!.url!!.substring(order!!.url!!.lastIndexOf('.')+1)
                if(extension == "pdf") {
                    prodImage!!.setImageResource(R.drawable.ic_pdf)
                    totalLabel!!.text = resources.getString(R.string.total_per_page)
                    if(order!!.prepared) {
                        totalLabel!!.text = resources.getString(R.string.total)
                        totalField!!.text = (order!!.prixTotal!!).toString()+" * "+order!!.pageCount+" pages"+
                                " = "+((order!!.prixTotal!!*order!!.pageCount))
                    }
                }
                else {
                    prodImage!!.setImageURI(uri)
                    totalLabel!!.text = resources.getString(R.string.total)
                    if(goodieType == Goodies.GoodiesType.T_Shirt.toString() && order!!.prepared) {
                        totalField!!.text = (order!!.prixTotal!!).toString()+" * "+order!!.pageCount+" pages"+
                                " = "+((order!!.prixTotal!!*order!!.pageCount))
                    }
                }
            }
            .addOnFailureListener{
                Log.e("Error Image Load",it.message)
                Toast.makeText(this, "Error Image Load!", Toast.LENGTH_SHORT).show()
            }
    }
}
