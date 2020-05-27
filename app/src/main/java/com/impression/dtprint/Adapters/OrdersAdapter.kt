package com.impression.dtprint.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.impression.dtprint.R
import com.impression.dtprint.models.Commande

class OrdersAdapter(options : FirestoreRecyclerOptions<Commande>)
    : FirestoreRecyclerAdapter<Commande, OrdersAdapter.OrdersHolder>(options)
{
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_orders, parent, false)
        return  OrdersHolder(view)
    }

    override fun onBindViewHolder(holder: OrdersHolder, position: Int, model: Commande) {
            holder.setData(model)
    }

    inner class OrdersHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var nomQttView:TextView? = null
        var orderNumView: TextView? = null
        var prixView:TextView? = null
        var date: TextView? = null

        var detailBtn: ImageView? = null


        init {
            nomQttView = itemView.findViewById(R.id.orders_prodNameQtt)
            orderNumView = itemView.findViewById(R.id.orders_orderNum)
            prixView = itemView.findViewById(R.id.orders_price)
            date = itemView.findViewById(R.id.orders_date)
            detailBtn = itemView.findViewById(R.id.orders_btn_more)

            detailBtn!!.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION && listener != null){
                    listener!!.onDetailClick(snapshots.getSnapshot(pos), pos)
                }
            }


        }


        fun setData(commande: Commande){
            if(commande.goodie !== null){
                nomQttView!!.text = commande.goodie!!.nom+" x "+commande.qtt
            }
            else if(commande.document !== null){
                nomQttView!!.text = commande.document!!.nom+" x "+commande.qtt
            }
            orderNumView!!.text = "N°: "+commande.numCommande
            prixView!!.text = "%.2f".format(commande.prixTotal).toString()+" DH"
            if(commande.dateCommande !==null){
//                dateView!!.text = commande!!.dateCommande!!.trim()
                date!!.text = commande!!.dateCommande!!.trim()
            }
            else{
//                dateView!!.text = "not date yet"
                date!!.text = "not date yet"
            }
        }
    }

    public interface OnItemClickListener{
        fun onDetailClick(documentSnapshot: DocumentSnapshot, position: Int)

    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

}
