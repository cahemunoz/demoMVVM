package com.cahemunoz.demomvvm

import com.cahemunoz.demomvvm.business.user.UserService
import com.cahemunoz.demomvvm.business.user.impl.UserServiceImpl
import com.cahemunoz.demomvvm.business.user.repositories.UserLocalRepository
import com.cahemunoz.demomvvm.business.user.repositories.UserRemoteRepository
import com.cahemunoz.demomvvm.presentation.user_list.viewmodels.UserListViewModel
import com.cahemunoz.demomvvm.repositories.user.RealmUserRepository
import com.cahemunoz.demomvvm.repositories.user_api.RetrofitUserApiRepository
import io.realm.Realm
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val moduleTest = module {

    single<UserLocalRepository> {
        RealmUserRepository(Realm.getDefaultInstance())
    }
    single<UserRemoteRepository> {
        RetrofitUserApiRepository()
    }
    single<UserService> {
        UserServiceImpl(get(), get())
    }
    viewModel { UserListViewModel(get()) }
}