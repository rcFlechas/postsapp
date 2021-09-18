package com.example.postsapp.application.di

import androidx.room.Room
import com.example.postsapp.models.data.local.DataBase
import org.koin.dsl.module

val roomModule = module {
    single { Room.databaseBuilder(get(), DataBase::class.java, "database_post").build() }
    single { get<DataBase>().postDAO() }
    single { get<DataBase>().userDAO() }
}
