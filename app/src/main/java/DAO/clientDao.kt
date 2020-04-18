package DAO
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open  class clientDao : RealmObject() {
    @PrimaryKey
    var id: Int =0
    var username: String ="client"
    var password: String ="pass"
    var nom: String ="zineb"
    var prenom: String ="chacrone"
    var ville : String= "marrakech"
    var adresse :String ="ensa"
    var email : String = "test@gmail.com"
    var numCarteBancaire:Long ?=123456

    }

