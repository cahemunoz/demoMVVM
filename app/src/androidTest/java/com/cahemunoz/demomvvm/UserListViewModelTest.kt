package com.cahemunoz.demomvvm


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cahemunoz.demomvvm.infra.realm_viewmodels.UserListViewModel
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListViewModelTest {

    private val viewModel by lazy { UserListViewModel(ApplicationProvider.getApplicationContext()) }

    @Test
    fun shouldWork() {
        viewModel.observeUserList()
        println(viewModel.userList.value)
    }
}