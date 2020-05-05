package com.impression.dtprint.models



object CurrentClient {
        var user: Client? = null
        var loggedIn: Boolean = false


    fun login(client: Client){
        user = client
        loggedIn = true
    }

    fun logout(){
        user = null
        loggedIn = false
    }
}
