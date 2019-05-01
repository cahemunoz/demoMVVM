package com.cahemunoz.demomvvm.repositories.user_remote

import com.cahemunoz.demomvvm.services.entities.User

class GithubUser {
    var login: String? = null
    var url: String? = null

    fun toUser(): User {
        return User().apply {
            this.email = this@GithubUser.url
            this.username = this@GithubUser.login
        }
    }

}