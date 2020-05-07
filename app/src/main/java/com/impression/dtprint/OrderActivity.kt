package com.impression.dtprint

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.DocumentSnapshot
import com.impression.dtprint.Adapters.LoginTabAdapter
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderActivity : AppCompatActivity() {

    private var prodNameField: TextView? = null
    private var priceField: TextView? = null
    private var formatField: TextView? = null
    private var doubleFaceField: CheckBox? = null
    private var goodieTypeField: TextView? = null
    private var stockField: TextView? = null
    private var descriptionField: TextView? = null
    private var qttField: EditText? = null
    private var shippingAddressField: EditText? = null
    private var totalField: TextView? = null
    private var orderBtn: Button? = null

    private var prodName: String? = null
    private var price: Float? = null
    private var format: String? = null
    private var doubleFace: Boolean? = null
    private var goodieType: String? = null
    private var stock: Int? = null
    private var description: String? = null
    private var qtt: Int? = null
    private var shippingAddress: String? = null
    private var total: Float? = null

    private var prodId: String? = null

    val db = ConnectionDB.db
    val productCollection = db.collection("Produits")
    val orderCollection = db.collection("Commandes")

    var prodtype: String? = null
    var documentSnapshot: DocumentSnapshot? = null

    var confirm = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val bundle = intent.extras
        prodId = bundle!!.getString("ProdId")
        val id = prodId

        CurrentClient.aboutToOrder = false

        if (prodId!![0] == '1') {
            prodtype = "Document"
        } else if (prodId!![0] == '2') {
            prodtype = "Goodie"
        }


        prodNameField = findViewById(R.id.order_view_product_name)
        priceField = findViewById(R.id.order_view_price)
        formatField = findViewById(R.id.order_view_format)
        doubleFaceField = findViewById(R.id.order_checkBox_doubleFace)
        goodieTypeField = findViewById(R.id.order_view_type)
        stockField = findViewById(R.id.order_view_stock)
        descriptionField = findViewById(R.id.order_view_description)
        qttField = findViewById(R.id.order_numPicker_qtt)
        shippingAddressField = findViewById(R.id.order_view_shipping_address)
        totalField = findViewById(R.id.order_view_total)
        orderBtn = findViewById(R.id.order_activity_order_btn)



        productCollection.document(prodId!!).get()
            .addOnSuccessListener {
                if (prodtype == "Document") {
                    val prod = it.toObject(Documents::class.java)
                    prodNameField!!.text = prod!!.nom
                    priceField!!.text = ""+prod!!.prix
                    formatField!!.text = prod!!.format
                    documentSnapshot = it
                } else if (prodtype == "Goodie") {
                    val prod = it.toObject(Goodies::class.java)
                    prodNameField!!.text = prod!!.nom
                    priceField!!.text = ""+prod!!.prix
                    goodieTypeField!!.text = prod!!.goodieType
                    stockField!!.text = ""+prod!!.stock
                    descriptionField!!.text = prod!!.description
                    documentSnapshot = it
                }
            }


        if (shippingAddress == "") {
            shippingAddress = CurrentClient.user!!.adresse
        }

        orderBtn!!.setOnClickListener {
            if(!confirm){
                prodName = prodNameField!!.text.toString()
                price = priceField!!.text.toString().toFloat()
                if (prodtype == "Document") {
                    format = formatField!!.text.toString()
                    doubleFace = doubleFaceField!!.isChecked
                } else if (prodtype == "Goodie") {
                    goodieType = goodieTypeField!!.text.toString()
                    stock = stockField!!.text.toString().toInt()
                    description = descriptionField!!.text.toString()
                }

                qtt = qttField!!.text.toString().trim().toInt()
                shippingAddress = shippingAddressField!!.text.toString().trim()


                if(qtt == null){
                    Toast.makeText(this, "Insert Quantity !", Toast.LENGTH_SHORT).show()
                }
                else{
                    total = price!! * qtt!!
                    totalField!!.text = ""+total
                    confirm = true
                    orderBtn!!.text = "Confirm"
                }
            }
            else{
                var commande = Commande(CurrentClient.user, qtt, shippingAddress, total)

                if (prodtype == "Document") {
                    commande.document = documentSnapshot!!.toObject(Documents::class.java)
                } else if (prodtype == "Goodie") {
                    commande.goodie = documentSnapshot!!.toObject(Goodies::class.java)
                }


                commande.dateCommande = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy [HH:mm]"))
                    .toString()

                orderCollection.document().set(commande)
                Toast.makeText(this, "Thank you !", Toast.LENGTH_SHORT).show()
                finish()
            }


        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
