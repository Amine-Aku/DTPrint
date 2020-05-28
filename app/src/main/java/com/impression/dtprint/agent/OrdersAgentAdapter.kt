package com.impression.dtprint.agent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.impression.dtprint.R
import com.impression.dtprint.models.Commande
import com.impression.dtprint.models.Goodies

class OrdersAgentAdapter(options : FirestoreRecyclerOptions<Commande>)
    : FirestoreRecyclerAdapter<Commande, OrdersAgentAdapter.OrdersHolder>(options)
{
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_orders_agent, parent, false)
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
        var pageCountView: EditText? = null
        var validate: CheckBox? = null


        init {
            nomQttView = itemView.findViewById(R.id.agent_orders_prodNameQtt)
            orderNumView = itemView.findViewById(R.id.agent_orders_orderNum)
            prixView = itemView.findViewById(R.id.agent_orders_price)
            date = itemView.findViewById(R.id.agent_orders_date)
            detailBtn = itemView.findViewById(R.id.agent_orders_btn_more)
            pageCountView = itemView.findViewById(R.id.agent_orders_pagesCount)
            validate = itemView.findViewById(R.id.agent_orders_validate)

            detailBtn!!.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION && listener != null){
                    listener!!.onDetailClick(snapshots.getSnapshot(pos), pos)
                }
            }

            validate!!.setOnCheckedChangeListener { buttonView, isChecked ->
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION && listener != null){
                    var count: Int? = null
                    if(!pageCountView!!.text.toString().trim().isEmpty())
                         count = pageCountView!!.text.toString().trim().toInt()
                    else
                         count = 1


                    listener!!.onValidate(snapshots.getSnapshot(pos), pos, isChecked, count!!)
                }
            }


        }


        fun setData(commande: Commande){
            if(commande.goodie !== null){
                nomQttView!!.text = commande.goodie!!.nom+" x "+commande.qtt
                if(commande.goodie!!.goodieType == Goodies.GoodiesType.T_Shirt.toString())
                    pageCountView!!.setText(""+commande.pageCount)
                else{
                    pageCountView!!.visibility = View.GONE
                }
            }
            else if(commande.document !== null){
                nomQttView!!.text = commande.document!!.nom+" x "+commande.qtt
                pageCountView!!.setText(""+commande.pageCount)
            }
            orderNumView!!.text = "N°: "+commande.numCommande + " => Client: "+commande.client!!.username

            
            validate!!.isChecked = commande.prepared


            if(commande!!.prepared) {
                prixView!!.text = "%.2f".format(commande.prixTotal!!*commande.pageCount).toString()+" DH"
            }
            else{
                prixView!!.text = "Order in process"
            }

            if(commande.dateCommande !==null){
                date!!.text = commande!!.dateCommande!!.trim()
            }
            else{
                date!!.text = "not date yet"
            }


        }


    }

    public interface OnItemClickListener{
        fun onDetailClick(documentSnapshot: DocumentSnapshot, position: Int)
        fun onValidate(documentSnapshot: DocumentSnapshot, position: Int, isChecked: Boolean, pageCount: Int = 1)

    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

}
