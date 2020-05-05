package com.impression.dtprint.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.impression.dtprint.R
import com.impression.dtprint.models.Produits
import kotlinx.android.synthetic.main.item_gallery.view.*

class ProduitsAdapter(options : FirestoreRecyclerOptions<Produits>)
    : FirestoreRecyclerAdapter<Produits, ProduitsAdapter.ProduitHolder>(options)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduitHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return  ProduitHolder(view)
    }

    override fun onBindViewHolder(holder: ProduitHolder, position: Int, model: Produits) {
           // holder.setData(model)
        holder.nomView!!.text = model.nom
        holder.prixView!!.prix_produit_gallery.text = "%.2f".format(model.prix).toString()+" DH"
    }

    inner class ProduitHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var nomView:TextView? = null
        var prixView:TextView? = null

        init {
            nomView = itemView.findViewById(R.id.nom_produit_gallery)
            prixView = itemView.findViewById(R.id.prix_produit_gallery)
        }


        fun setData(produit: Produits){
            nomView!!.text = produit.nom
            prixView!!.prix_produit_gallery.text = "%.2f".format(produit.prix).toString()+" DH"
        }
    }

}
