package com.impression.dtprint.fragments

import android.content.Intent
import android.net.MailTo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.impression.dtprint.R

class ContactFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_contact, container, false)

        val email: TextView = v.findViewById(R.id.contact_email)
        val tel: TextView = v.findViewById(R.id.contact_tel)
        val address: TextView = v.findViewById(R.id.contact_address)

        email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, resources.getString(R.string.email_address))
            intent.setType("text/plain")
            startActivity(Intent.createChooser(intent, "Send an Email"))
        }

        tel.setOnClickListener {
//            val intent = Intent(Intent.ACTION_CALL)
//            intent.setData(Uri.parse(resources.getString(R.string.tel)))
//            startActivity(intent)
        }

        address.setOnClickListener {
//            val intent = Intent(Intent.ACTION_SEND)
//            startActivity(intent)
        }

        return v
    }
}
