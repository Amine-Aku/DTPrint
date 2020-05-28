package com.impression.dtprint.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.impression.dtprint.LoginActivity
import com.impression.dtprint.R
import com.impression.dtprint.dao.ClientController
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.Client

class SignUpFragment : Fragment() {

    // Database Initialisation
    private val  db = ConnectionDB.db
    private val collection = db.collection("Client")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        // XML Views Affectation
        val btnSignUp = view.findViewById<Button>(R.id.signUp_btn)

        val nomField = view.findViewById<EditText>(R.id.input_signUp_nom)
        val prenomField = view.findViewById<EditText>(R.id.input_signUp_prenom)
        val usernameField = view.findViewById<EditText>(R.id.input_signUp_username)
        val emailField = view.findViewById<EditText>(R.id.input_signUp_email)
        val passwordField = view.findViewById<EditText>(R.id.input_signUp_password)
        val villeField = view.findViewById<EditText>(R.id.input_signUp_ville)
        val adresseField = view.findViewById<EditText>(R.id.input_signUp_adresse)
        val numTelField = view.findViewById<EditText>(R.id.input_signUp_numTel)


        // Sign Up button
        btnSignUp.setOnClickListener {
                val client = Client(
                    usernameField.text.toString().trim(),
                    passwordField.text.toString().trim(),
                    nomField.text.toString().trim(),
                    prenomField.text.toString().trim(),
                    villeField.text.toString().trim(),
                    adresseField.text.toString().trim(),
                    emailField.text.toString().trim(),
                    numTelField.text.toString().trim()
                )

            collection.whereEqualTo("username", client.username).get()
                .addOnSuccessListener {
                    if(it.isEmpty){
                        collection.document().set(client)
                            .addOnSuccessListener {
                                Toast.makeText(activity!!, "Client Added", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {
                                Log.e("Error !",it.message)
                                Toast.makeText(activity!!, "Error !", Toast.LENGTH_SHORT).show()
                            }
                    }
                    else
                    Toast.makeText(activity!!, "Username Already used", Toast.LENGTH_SHORT).show()
                }
        }

        return view
    }
}
