package com.impression.dtprint.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.impression.dtprint.R
import com.impression.dtprint.models.Client
import com.impression.dtprint.models.CurrentClient



class ProfileFragment : Fragment() {

    var nomField:TextView? = null
    var prenomField:TextView? = null
    var usernameField:TextView? = null
    var emailField:TextView? = null
    var cityField:TextView? = null
    var adresseField:TextView? = null
    var numTelField:TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val v = inflater.inflate(R.layout.fragment_profile, container, false)

         nomField = v.findViewById(R.id.profile_input_nom)
         prenomField = v.findViewById(R.id.profile_input_prenom)
         usernameField = v.findViewById(R.id.profile_input_username)
         emailField = v.findViewById(R.id.profile_input_email)
         cityField = v.findViewById(R.id.profile_input_ville)
         adresseField = v.findViewById(R.id.profile_input_adresse)
        numTelField = v.findViewById(R.id.profile_input_numTel)

        if(CurrentClient.loggedIn){
            populateTextViews(CurrentClient.user!!)
        }



        return v
    }

    fun populateTextViews(client: Client){
        nomField!!.text = client.nom
        prenomField!!.text = client.prenom
        usernameField!!.text = client.username
        emailField!!.text = client.email
        cityField!!.text = client.ville
        adresseField!!.text = client.adresse
        numTelField!!.text = client.numTel
    }
}
