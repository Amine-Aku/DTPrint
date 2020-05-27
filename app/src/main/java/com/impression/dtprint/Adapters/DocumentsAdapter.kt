package com.impression.dtprint.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.impression.dtprint.R
import com.impression.dtprint.models.Documents
import kotlinx.android.synthetic.main.item_document.view.*

class DocumentsAdapter(val context: Context, private val documents: List<Documents>)
    : RecyclerView.Adapter<DocumentsAdapter.MyViewHolder>(){

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_document, parent, false)



        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val document = documents[position]
        holder.SetData(document, position)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val orderBtn = itemView.findViewById<Button>(R.id.document_order_btn)

        fun SetData(document: Documents, pos: Int){
            document?.let {
                itemView.document_name.text = document.nom
                itemView.document_price.text = "%.2f".format(document.prix).toString()+" DH"
            }
        }

        init {
            orderBtn.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION && listener != null)
                    listener!!.onOrderClick(documents, pos)
            }
        }




        }

        public interface OnItemClickListener{
            fun onOrderClick(l: List<Documents>, position: Int)
        }

        fun setOnItemClickListener(listener: OnItemClickListener){
            this.listener = listener
        }



    }

