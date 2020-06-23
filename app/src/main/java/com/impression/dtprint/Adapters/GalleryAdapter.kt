package com.impression.dtprint.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.impression.dtprint.MainActivity
import com.impression.dtprint.OrderActivity
import com.impression.dtprint.R
import com.impression.dtprint.fragments.GalleryFragment
import com.impression.dtprint.models.ProductImages
import com.impression.dtprint.models.Produits
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter(val context: Context, private val produits: List<Produits>)
    : RecyclerView.Adapter<GalleryAdapter.MyViewHolder>(){

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val produit = produits[position]
        holder.SetData(produit, position)
    }
    override fun getItemCount(): Int {
        return produits.size
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val orderBtn = itemView.order_btn
        val wishList = itemView.wishlit_btn
        val image = itemView.prod_img

        fun SetData(produit: Produits, pos: Int){
            produit?.let {
                itemView.nom_produit_gallery.text = produit.nom
                itemView.prix_produit_gallery.text = "%.2f".format(produit.prix).toString()+" DH"
                val id = produit.id-20
                if(id>=0)
                    image.setImageResource(ProductImages.id[(id).toInt()])
                else
                    image.setImageResource(R.drawable.p10)

            }
        }

        init {
            orderBtn.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION && listener != null)
                    listener!!.onOrderClick(produits, pos)
            }

            wishList.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION && listener != null)
                    listener!!.onWishlistClick(produits, pos)
            }

        }




        }

        public interface OnItemClickListener{
            fun onOrderClick(l: List<Produits>, position: Int)
            fun onWishlistClick(l: List<Produits>, position: Int)
            fun onMoreClick(l: List<Produits>, position: Int)

        }

        fun setOnItemClickListener(listener: OnItemClickListener){
            this.listener = listener
        }



    }

