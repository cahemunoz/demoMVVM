package com.cahemunoz.demomvvm

import com.cahemunoz.demomvvm.repositories.user_api.RetrofitUserApiRepository
import org.junit.Test

class RetrofitUserApiRepositoryTest {
    private val userApiRepository = RetrofitUserApiRepository()

    @Test
    fun shouldRetrieveUsers() {
        val flow = userApiRepository.findAllUsers()
        val users = flow.blockingFirst()
        users.forEach {
            println("username: ${it.username}  email: ${it.email}")
        }
    }

}