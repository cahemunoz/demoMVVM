package com.cahemunoz.demomvvm.presentation._base

import io.realm.Realm

open class RealmRxViewModel: RxViewModel() {
    val realmOnUiThread = Realm.getDefaultInstance()

    override fun onCleared() {
        super.onCleared()
        realmOnUiThread.close()
    }

}