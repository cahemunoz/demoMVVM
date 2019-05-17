package com.outsmart.baseprojectkotlin.data_sources.user_remote

import com.outsmart.baseprojectkotlin.services._entities.User

class GithubUserApiModel {
    var login: String? = null
    var url: String? = null

    fun toUser(): User {
        return User().apply {
            this.email = this@GithubUserApiModel.url
            this.username = this@GithubUserApiModel.login
        }
    }

}