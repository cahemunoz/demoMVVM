package com.outsmart.baseprojectkotlin.data_sources.user_remote;


import io.reactivex.Single;
import org.jetbrains.annotations.NotNull;
import retrofit2.http.GET;

import java.util.List;


public interface RetrofitUserApi {

    @NotNull
    @GET("/users")
    Single<List<GithubUserApiModel>> findAllUsers();

}
