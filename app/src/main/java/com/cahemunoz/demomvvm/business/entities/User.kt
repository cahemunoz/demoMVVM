package com.cahemunoz.demomvvm.business.entities

import io.realm.RealmObject


open class User: RealmObject() {
    var username:String? = null
    var email:String? = null
}