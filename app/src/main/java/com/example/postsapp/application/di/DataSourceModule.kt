package com.example.postsapp.application.di

import com.example.postsapp.models.data.source.*
import org.koin.dsl.module

val dataSourceModule = module {
    single<PostLocalDataSource> { PostDataSource(get(), get()) }
    single<PostRemoteDataSource> { PostDataSource(get(), get()) }
    single<UserLocalDataSource> { UserDataSource(get(), get()) }
    single<UserRemoteDataSource> { UserDataSource(get(), get()) }
}