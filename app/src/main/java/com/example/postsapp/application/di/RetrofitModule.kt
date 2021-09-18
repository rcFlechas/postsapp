package com.example.postsapp.application.di

import com.example.postsapp.core.RetrofitFactory
import com.example.postsapp.models.data.remote.api.PostApi
import com.example.postsapp.models.data.remote.api.UserApi
import com.example.postsapp.models.data.remote.rest.JsonPlaceHolder.URL_BASE
import org.koin.dsl.module

val retrofitModule = module {
    factory { RetrofitFactory.retrofit(URL_BASE).create(PostApi::class.java) }
    factory { RetrofitFactory.retrofit(URL_BASE).create(UserApi::class.java) }
}
