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
import com.impression.dtprint.dao.ClientDao
import com.impression.dtprint.models.Client
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_signup.*
import models.User

class SignUpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        val btnSignUp = view.findViewById<Button>(R.id.signUp_btn)
        val btnGet = view.findViewById<Button>(R.id.get_btn)
        val btnUpdate = view.findViewById<Button>(R.id.update_btn)
        val btnDelete = view.findViewById<Button>(R.id.delete_btn)

        val nomField = view.findViewById<EditText>(R.id.input_signUp_nom)
        val prenomField = view.findViewById<EditText>(R.id.input_signUp_prenom)
        val usernameField = view.findViewById<EditText>(R.id.input_signUp_username)
        val emailField = view.findViewById<EditText>(R.id.input_signUp_email)
        val passwordField = view.findViewById<EditText>(R.id.input_signUp_password)
        val villeField = view.findViewById<EditText>(R.id.input_signUp_ville)
        val adresseField = view.findViewById<EditText>(R.id.input_signUp_adresse)


        btnSignUp.setOnClickListener {
            ClientDao.Instance._instace.addClient(
                Client(usernameField.text.toString(), passwordField.text.toString(),nomField.text.toString(), prenomField.text.toString(),
                    villeField.text.toString(), adresseField.text.toString(),emailField.text.toString())
            )
            Toast.makeText(activity, "Client Added", Toast.LENGTH_LONG).show()
        }

        btnGet.setOnClickListener {
            val result = ClientDao.Instance._instace.getAll()

            var k = ""
            result.forEach{ i->
                var id: Long? = i.id
                var nom: String? = i.nom
                var prenom: String? = i.prenom
                var username: String? = i.userName
                k = "$id $nom $prenom $username \n"
            }
            Toast.makeText(activity, k, Toast.LENGTH_LONG).show()
            print(k)
        }

        return view
    }

    fun getInputValues(){

    }


}
