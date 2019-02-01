package com.cahemunoz.demomvvm.domain.models

import io.realm.RealmObject


open class User: RealmObject() {
    var username:String? = null
    var email:String? = null
}