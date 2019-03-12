package com.cahemunoz.demomvvm

import com.cahemunoz.demomvvm.business.user.UserService
import com.cahemunoz.demomvvm.business.user.impl.UserServiceImpl
import com.cahemunoz.demomvvm.presentation.user_list.viewmodels.UserListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val moduleTeste = module {
    single<UserService> {
        UserServiceImpl()
    }
    viewModel { UserListViewModel(get()) }
}

val teste = module {
    single<UserService> {
        UserServiceImpl()
    }
    viewModel { UserListViewModel(get()) }
}

