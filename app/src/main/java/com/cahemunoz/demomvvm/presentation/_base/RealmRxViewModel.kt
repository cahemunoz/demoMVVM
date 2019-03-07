package com.cahemunoz.demomvvm.presentation._base

import io.realm.Realm

open class RealmRxViewModel: RxViewModel() {

    /**
     * This realm instance should be only used on Android's UI thread
     * avoid to commit changes on this instance.
     * nearly it will be read only
     */
    val realmOnUiThread:Realm = Realm.getDefaultInstance()

    override fun onCleared() {
        super.onCleared()
        realmOnUiThread.close()
    }

}