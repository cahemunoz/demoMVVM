package com.cahemunoz.demomvvm.repositories.user_api

import com.cahemunoz.demomvvm.business.entities.User

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