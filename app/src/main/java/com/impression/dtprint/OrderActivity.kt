package com.impression.dtprint

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.impression.dtprint.Adapters.LoginTabAdapter
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.*
import kotlinx.android.synthetic.main.activity_order.*
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderActivity : AppCompatActivity() {

    // Views
    private var prodImage: ImageView? = null
    private var prodNameField: TextView? = null
    private var priceField: TextView? = null
    private var formatField: TextView? = null
    private var doubleFaceField: CheckBox? = null
    private var goodieTypeField: TextView? = null
    private var stockField: TextView? = null
    private var descriptionField: TextView? = null

    private var qttField: EditText? = null
    private var uploadImageBtn: Button? = null
    private var uploadFileBtn: Button? = null
    private var noteField: EditText? = null
    private var shippingAddressField: EditText? = null
    private var totalField: TextView? = null
    private var orderBtn: Button? = null

    private var progressBar: ProgressBar? = null
    private var totalLabel: TextView? = null


    // VARs
    private var prodName: String? = null
    private var price: Float? = null
    private var format: String? = null
    private var doubleFace: Boolean? = null
    private var goodieType: String? = null
    private var stock: Int? = null
    private var description: String? = null

    private var uploadUrl: String? = null
    private var note: String? = null
    private var qtt: Int? = null
    private var shippingAddress: String? = null
    private var total: Float? = null

    private var imageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1
    private val PICK_File_REQUEST = 2

    private var prodId: String? = null

    val db = ConnectionDB.db
    val productCollection = db.collection("Produits")
    val orderCollection = db.collection("Commandes")
    val storageRef = Firebase.storage("gs://dtprint-5c1da.appspot.com").reference
    var uploadTask: StorageTask<UploadTask.TaskSnapshot>? = null

    var prodtype: String? = null
    var documentSnapshot: DocumentSnapshot? = null

    var confirm = false
    var fileLoaded = false

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


        prodImage = findViewById(R.id.order_product_image)
        prodNameField = findViewById(R.id.order_view_product_name)
        priceField = findViewById(R.id.order_view_price)
        formatField = findViewById(R.id.order_view_format)
        doubleFaceField = findViewById(R.id.order_checkBox_doubleFace)
        goodieTypeField = findViewById(R.id.order_view_type)
        stockField = findViewById(R.id.order_view_stock)
        descriptionField = findViewById(R.id.order_view_description)

        noteField = findViewById(R.id.order_view_note)
        qttField = findViewById(R.id.order_numPicker_qtt)
        shippingAddressField = findViewById(R.id.order_view_shipping_address)
        totalField = findViewById(R.id.order_view_total)

        progressBar = findViewById(R.id.progress_bar)
        uploadImageBtn = findViewById(R.id.upload_btn_image)
        uploadFileBtn = findViewById(R.id.upload_btn_file)
        orderBtn = findViewById(R.id.order_activity_order_btn)

        totalLabel = findViewById(R.id.total_label)


        // Load the Choosen Product
        productCollection.document(prodId!!).get()
            .addOnSuccessListener {
                if (prodtype == "Document") {
                    val prod = it.toObject(Documents::class.java)
                    prodNameField!!.text = prod!!.nom
                    priceField!!.text = ""+prod!!.prix
                    formatField!!.text = prod!!.format
                    documentSnapshot = it
                    //Hide Uneccessary Input Field
                    val goodiesDetails: LinearLayout = findViewById(R.id.order_goodie_details)
                    goodiesDetails.visibility = View.GONE
                } else if (prodtype == "Goodie") {
                    val prod = it.toObject(Goodies::class.java)
                    prodNameField!!.text = prod!!.nom
                    priceField!!.text = ""+prod!!.prix
                    goodieTypeField!!.text = prod!!.goodieType
                    stockField!!.text = ""+prod!!.stock
                    descriptionField!!.text = prod!!.description
                    documentSnapshot = it
                    //Hide Uneccessary Input Field
                    val documentDetails: LinearLayout = findViewById(R.id.order_document_details)
                    documentDetails.visibility = View.GONE
                }
            }


        if (shippingAddressField!!.text.toString().trim() == "") {
            shippingAddress = CurrentClient.user!!.adresse
        }


        uploadImageBtn!!.setOnClickListener {
            if(!confirm){
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
                totalLabel!!.text = resources.getString(R.string.total)
                fileLoaded = true
            }
        }

        uploadFileBtn!!.setOnClickListener {
            if(!confirm){
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.setType("application/pdf")
                startActivityForResult(intent, PICK_File_REQUEST)
                totalLabel!!.text = resources.getString(R.string.total_per_page)
                fileLoaded = true
            }
        }

        orderBtn!!.setOnClickListener {
            if(!confirm && fileLoaded){
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
                if(shippingAddressField!!.text.toString().trim() == "")
                    shippingAddress = CurrentClient.user!!.adresse
                else
                    shippingAddress = shippingAddressField!!.text.toString().trim()
                note = noteField!!.text.toString().trim()

                // Calculate the Total


                    if(qtt == null){
                        qttField!!.setText(""+1)
                        qtt = 1
                    }
                    total = price!! * qtt!!
                    totalField!!.text = ""+total
                    confirm = true
                    orderBtn!!.text = "Confirm"

                uploadFileBtn!!.visibility = View.GONE
                uploadImageBtn!!.visibility = View.GONE

            }
            else if(uploadTask != null && uploadTask!!.isInProgress){
                Toast.makeText(this, "Another Upload is in Progress", Toast.LENGTH_SHORT).show()
            }
            else{

                if(imageUri != null){
                    Toast.makeText(this, "Uploading ...", Toast.LENGTH_SHORT).show()
                    orderCollection.get()
                        .addOnSuccessListener {
                            var nextId: Int? = null
                            if(it.isEmpty)  nextId = 1
                            else nextId = it.last().id.toInt()+1
                            val IDstr = "%04d".format(nextId)

                            val path = "Commandes/"+IDstr+"_"+CurrentClient.user!!.username+"_"+
                                    System.currentTimeMillis().toString()+"."+getFileExtension(imageUri!!)
                            val fileRef = storageRef.child(path)
                            uploadTask = fileRef.putFile(imageUri!!)
                                .addOnSuccessListener {
                                    uploadUrl = path

                                    var commande = Commande(CurrentClient.user, qtt, shippingAddress, total, uploadUrl, note)

                                    if (prodtype == "Document") {
                                        commande.document = documentSnapshot!!.toObject(Documents::class.java)
                                        commande.document!!.doubleFaces = doubleFaceField!!.isChecked
                                    } else if (prodtype == "Goodie") {
                                        commande.goodie = documentSnapshot!!.toObject(Goodies::class.java)
                                    }

                                    commande.dateCommande = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                                        .toString()
                                    commande.numCommande = IDstr
                                    orderCollection.document(IDstr).set(commande)

                                    Toast.makeText(this, "Thank you !", Toast.LENGTH_SHORT).show()
                                    fileLoaded = false
                                    // Backbutton method and refresh Main Activity
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                                }
                                .addOnProgressListener {
                                    val progress = (100.0 * it.bytesTransferred / it.totalByteCount)
                                    progressBar!!.setProgress(progress.toInt())
                                }




                        }




                }
                else{
                    Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show()
                    println("No File Selected !!")
                }
                //

            }
        }

    }


    private fun getFileExtension(uri: Uri): String{
        val cr = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(uri))!!
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
            data != null && data.data != null ){
            imageUri = data.data

            order_product_image.setImageURI(imageUri)
            totalLabel!!.text = resources.getString(R.string.total)
        }
        else if (requestCode == PICK_File_REQUEST && resultCode == RESULT_OK &&
            data != null && data.data != null ){
            imageUri = data.data
            order_product_image.setImageResource(R.drawable.ic_pdf)
            totalLabel!!.text = resources.getString(R.string.total_per_page)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Backbutton method and refresh Main Activity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
