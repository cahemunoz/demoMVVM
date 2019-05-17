package com.cahemunoz.demomvvm.di

import com.cahemunoz.demomvvm.services.user.UserService
import com.cahemunoz.demomvvm.services.user.impl.UserServiceImpl
import com.cahemunoz.demomvvm.services.user.repositories.UserLocalRepository
import com.cahemunoz.demomvvm.services.user.repositories.UserRemoteRepository
import com.cahemunoz.demomvvm.presentation.screens.user_list.viewmodels.UserListViewModel
import com.cahemunoz.demomvvm.repositories.user.RealmUserRepository
import com.cahemunoz.demomvvm.repositories.user_remote.RetrofitUserApiRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val userModule = module {

    single<UserLocalRepository> {
        RealmUserRepository()
    }
    single<UserRemoteRepository> {
        RetrofitUserApiRepository()
    }
    single<UserService> {
        UserServiceImpl(get(), get())
    }
    viewModel { UserListViewModel(get()) }
}