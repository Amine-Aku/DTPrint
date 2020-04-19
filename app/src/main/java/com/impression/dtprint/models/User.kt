package models

open class User(){
    var id: Long =0
    var userName:String ?= ""
    var password:String ?=""
    var type: UserType? = UserType.Client

    constructor(id: Long):this()
    {
        this.id=id
    }

//    init {
//        println("User Created $id $userName")
//    }

        constructor(id: Long ,username: String ,password: String, type: UserType = UserType.Client): this(){
            this.id = id
            this.userName = userName
            this.password = password
            this.type = type
        }

    enum class UserType {
        Client,Agent,Livreur
    }

}

