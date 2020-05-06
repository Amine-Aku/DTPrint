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
import com.impression.dtprint.models.Commande
import com.impression.dtprint.models.Produits
import kotlinx.android.synthetic.main.item_gallery.view.*

class OrdersAdapter(options : FirestoreRecyclerOptions<Commande>)
    : FirestoreRecyclerAdapter<Commande, OrdersAdapter.OrdersHolder>(options)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_orders, parent, false)
        return  OrdersHolder(view)
    }

    override fun onBindViewHolder(holder: OrdersHolder, position: Int, model: Commande) {
            holder.setData(model)
    }

    inner class OrdersHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var nomQttView:TextView? = null
        var dateView: TextView? = null
        var prixView:TextView? = null

        init {
            nomQttView = itemView.findViewById(R.id.orders_prodNameQtt)
            dateView = itemView.findViewById(R.id.orders_orderDate)
            prixView = itemView.findViewById(R.id.orders_price)
        }


        fun setData(commande: Commande){
            if(commande.goodie !== null){
                nomQttView!!.text = commande.goodie!!.nom+" x "+commande.qtt
            }
            else if(commande.document !== null){
                nomQttView!!.text = commande.document!!.nom+" x "+commande.qtt
            }
            prixView!!.text = "%.2f".format(commande.prixTotal).toString()+" DH"
            if(commande.dateCommande !==null){
                dateView!!.text = commande!!.dateCommande
            }
            else{
                dateView!!.text = "not date yet"
            }
        }
    }

}
