package com.cahemunoz.demomvvm.business.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class User: RealmObject() {
    @PrimaryKey
    var username:String? = null
    var email:String? = null
}