package com.outsmart.baseprojectkotlin.di

import com.outsmart.baseprojectkotlin.data_sources._base.RetrofitGenerator
import com.outsmart.baseprojectkotlin.data_sources.user_local.RealmUserDataSource
import com.outsmart.baseprojectkotlin.data_sources.user_remote.RetrofitUserApi
import com.outsmart.baseprojectkotlin.data_sources.user_remote.RetrofitUserDataSource
import com.outsmart.baseprojectkotlin.presentation.screens.user_list.UserListViewModel
import com.outsmart.baseprojectkotlin.services.user.UserService
import com.outsmart.baseprojectkotlin.services.user.impl.UserServiceImpl
import com.outsmart.baseprojectkotlin.services.user.repositories.UserLocalRepository
import com.outsmart.baseprojectkotlin.services.user.repositories.UserRemoteRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val userModule = module {
    viewModel { UserListViewModel(get()) }

    single<UserService> { UserServiceImpl(get(), get()) }

    single<UserLocalRepository> { RealmUserDataSource() }

    single<UserRemoteRepository> { RetrofitUserDataSource(get()) }

    single { get<RetrofitGenerator>().createRepository(RetrofitUserApi::class.java)  }

}