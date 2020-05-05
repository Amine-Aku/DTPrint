package models

open class User(){
    var id: Long =0
    var username:String ?= ""
    var password:String ?=""
    var type: UserType? = UserType.Client

    constructor(id: Long):this()
    {
        this.id=id
    }


        constructor(id: Long ,username: String ,password: String, type: UserType = UserType.Client): this(){
            this.id = id
            this.username = username
            this.password = password
            this.type = type
        }

    enum class UserType {
        Client,Agent,Livreur
    }

}

