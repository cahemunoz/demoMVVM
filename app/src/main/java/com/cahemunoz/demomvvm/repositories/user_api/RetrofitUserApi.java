package com.cahemunoz.demomvvm.repositories.user_api;


import io.reactivex.Single;
import org.jetbrains.annotations.NotNull;
import retrofit2.http.GET;

import java.util.List;


public interface RetrofitUserApi {

    @NotNull
    @GET("/users")
    Single<List<GithubUser>> findAllUsers();

}
