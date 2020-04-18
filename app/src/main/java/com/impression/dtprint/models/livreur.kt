package models

class livreur:user {
    var matricule:String ="livreur1"
    var ville :String ="marrakech"

    constructor(Id: Int, name: String, userPassword: String, matricule: String, ville: String) : super(Id, name, userPassword) {
        this.matricule = matricule
        this.ville = ville
        this.userName=matricule
    }
}
