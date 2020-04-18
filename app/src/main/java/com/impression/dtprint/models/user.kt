package models

open class user(){
    var id: Int =0
    get() {
        println("user get id")
        return field
    }
    set(value) {
        println("user set id")
    }

        var userName:String ?= "Null"
            get() {
                println("user get username")
                return field

            }
            set(value) {
                println("user set username")
                field=value
            }
        var password:String ?="Null"
            get() {
                println("user get password")
                return field

            }
            set(value) {
                println("user set passeword")
                field=value
            }
    constructor(n: Int):this()
    {
        this.id=n
    }
    init {
        println("hello user $id $userName")
    }

        constructor(Id: Int ,name: String ,userPassword: String): this(Id){
            this.id=Id
            this.userName =name
            this.password=userPassword
        }

}

