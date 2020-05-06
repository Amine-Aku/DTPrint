package com.impression.dtprint.Adapters

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.impression.dtprint.R
import com.impression.dtprint.models.Produits
import kotlinx.android.synthetic.main.item_gallery.view.*

class WishlistAdapter(options : FirestoreRecyclerOptions<Produits>)
    : FirestoreRecyclerAdapter<Produits, WishlistAdapter.WishlistHolder>(options)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return  WishlistHolder(view)
    }

    override fun onBindViewHolder(holder: WishlistHolder, position: Int, model: Produits) {
           // holder.setData(model)
        holder.nomView!!.text = model.nom
        holder.prixView!!.text = "%.2f".format(model.prix).toString()+" DH"
    }

    public fun deleteWishlistItem(position:Int){
        snapshots.getSnapshot(position).reference.delete()
    }

    inner class WishlistHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var nomView:TextView? = null
        var prixView:TextView? = null
        var wBtn: Button? = null

        init {
            nomView = itemView.findViewById(R.id.nom_produit_gallery)
            prixView = itemView.findViewById(R.id.prix_produit_gallery)
            wBtn = itemView.findViewById(R.id.wishlit_btn)
            wBtn!!.visibility = View.INVISIBLE
        }


        fun setData(produit: Produits){
            nomView!!.text = produit.nom
            prixView!!.prix_produit_gallery.text = "%.2f".format(produit.prix).toString()+" DH"
        }
    }

}
