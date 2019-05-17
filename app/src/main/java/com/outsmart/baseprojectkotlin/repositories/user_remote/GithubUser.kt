package com.outsmart.baseprojectkotlin.repositories.user_remote

import com.outsmart.baseprojectkotlin.services.entities.User

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