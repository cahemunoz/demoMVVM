package com.cahemunoz.demomvvm.repositories.user_api;

import com.cahemunoz.demomvvm.business.entities.User;
import com.cahemunoz.demomvvm.business.user.repositories.UserApiRepository;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import retrofit2.http.GET;

import java.util.List;

public interface RetrofitUserRepository extends UserApiRepository {

    @NotNull
    @GET("/users/find")
    @Override
    Flowable<List<User>> findAllUsers();

}
