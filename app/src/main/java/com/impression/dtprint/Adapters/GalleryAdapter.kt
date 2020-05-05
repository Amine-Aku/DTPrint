package com.impression.dtprint.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.impression.dtprint.OrderActivity
import com.impression.dtprint.R
import com.impression.dtprint.models.Produits
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter(val context: Context, private val produits: List<Produits>)
    : RecyclerView.Adapter<GalleryAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false)



        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return produits.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val produit = produits[position]
        holder.SetData(produit, position)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val orderBtn = itemView.findViewById<Button>(R.id.order_btn)

        fun SetData(produit: Produits, pos: Int){
            produit?.let {
                    itemView.nom_produit_gallery.text = produit.nom
                   itemView.prix_produit_gallery.text = "%.2f".format(produit.prix).toString()+" DH"
            }
        }


        }





    }

