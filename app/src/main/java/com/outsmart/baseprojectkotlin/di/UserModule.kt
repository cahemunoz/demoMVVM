package com.outsmart.baseprojectkotlin.di

import com.outsmart.baseprojectkotlin.services.user.UserService
import com.outsmart.baseprojectkotlin.services.user.impl.UserServiceImpl
import com.outsmart.baseprojectkotlin.services.user.repositories.UserLocalRepository
import com.outsmart.baseprojectkotlin.services.user.repositories.UserRemoteRepository
import com.outsmart.baseprojectkotlin.presentation.screens.user_list.viewmodels.UserListViewModel
import com.outsmart.baseprojectkotlin.repositories.user.RealmUserRepository
import com.outsmart.baseprojectkotlin.repositories.user_remote.RetrofitUserApiRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val userModule = module {
    viewModel { UserListViewModel(get()) }

    single<UserService> { UserServiceImpl(get(), get()) }

    single<UserLocalRepository> { RealmUserRepository() }

    single<UserRemoteRepository> { RetrofitUserApiRepository() }

}