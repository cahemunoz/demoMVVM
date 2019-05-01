package com.cahemunoz.demomvvm.repositories

import com.cahemunoz.demomvvm.repositories.user_remote.RetrofitUserApiRepository
import org.junit.Test

class RetrofitUserApiRepositoryTest {
    private val userApiRepository = RetrofitUserApiRepository()

    @Test
    fun shouldRetrieveUsers() {
        val flow = userApiRepository.findAllUsers()
        val users = flow.blockingGet()
        users.forEach {
            println("username: ${it.username}  email: ${it.email}")
        }
    }

}