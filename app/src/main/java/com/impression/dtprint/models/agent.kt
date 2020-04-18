package models

class agent :user{

    var matricule:String ="admin"

    constructor() : super()
    constructor(n: Int, matricule: String) : super(n) {
        this.matricule = matricule
    }

    constructor(Id: Int, name: String, userPassword: String,matrricule:String) : super(Id, name, userPassword) {
        this.matricule = matrricule
        this.userName=matrricule
    }

}
