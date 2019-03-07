package com.cahemunoz.demomvvm.repositories._base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepositoryGenerator {

    private const val BASE_URL = "https://api.github.com/"


    private val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logging)

    private val builder = Retrofit.Builder()
        .client(httpClient.build())
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()


    fun <S> createRepository(repositoryClass: Class<S>): S {
        return retrofit.create(repositoryClass)
    }
}