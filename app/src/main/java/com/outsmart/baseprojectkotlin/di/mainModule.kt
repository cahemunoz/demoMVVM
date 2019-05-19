package com.outsmart.baseprojectkotlin.di

import com.outsmart.baseprojectkotlin.Constants
import com.outsmart.baseprojectkotlin.data_sources._base.RetrofitGenerator
import org.koin.dsl.module.module

val mainModule = module{
    single { RetrofitGenerator(get("baseURL")) }
    single("baseURL") { Constants.BASE_URL }
}