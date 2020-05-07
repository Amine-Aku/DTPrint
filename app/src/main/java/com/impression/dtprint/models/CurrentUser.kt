package com.impression.dtprint.models



object CurrentClient {
        var user: Client? = null
        var loggedIn: Boolean = false
        var id: String? = null

        var aboutToOrder: Boolean = false


    fun login(client: Client, id: String){
        user = client
        loggedIn = true
        this.id = id
    }

    fun logout(){
        user = null
        loggedIn = false
        this.id = null
    }
}
