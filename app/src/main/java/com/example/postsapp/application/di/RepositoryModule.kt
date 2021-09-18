package com.example.postsapp.application.di

import com.example.postsapp.models.repositories.PostRepository
import com.example.postsapp.models.repositories.UserRepository
import com.example.postsapp.models.repositories.impl.PostRepositoryImpl
import com.example.postsapp.models.repositories.impl.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<PostRepository> { PostRepositoryImpl(get(), get()) }
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
}
