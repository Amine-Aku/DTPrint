package com.impression.dtprint.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.impression.dtprint.R
import com.impression.dtprint.dao.ClientController
import com.impression.dtprint.models.Client

class SignUpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        val btnSignUp = view.findViewById<Button>(R.id.signUp_btn)

        val nomField = view.findViewById<EditText>(R.id.input_signUp_nom)
        val prenomField = view.findViewById<EditText>(R.id.input_signUp_prenom)
        val usernameField = view.findViewById<EditText>(R.id.input_signUp_username)
        val emailField = view.findViewById<EditText>(R.id.input_signUp_email)
        val passwordField = view.findViewById<EditText>(R.id.input_signUp_password)
        val villeField = view.findViewById<EditText>(R.id.input_signUp_ville)
        val adresseField = view.findViewById<EditText>(R.id.input_signUp_adresse)


        btnSignUp.setOnClickListener {
            ClientController.addClient(
                activity!!,
                Client(
                    usernameField.text.toString(),
                    passwordField.text.toString(),
                    nomField.text.toString(),
                    prenomField.text.toString(),
                    villeField.text.toString(),
                    adresseField.text.toString(),
                    emailField.text.toString()
                )
            )
            Toast.makeText(activity, "Client Added", Toast.LENGTH_LONG).show()
        }


        fun getInputValues() {

        }

        return view
    }
}
