package com.example.postsapp.models.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postsapp.models.data.local.dao.PostDAO
import com.example.postsapp.models.data.local.dao.UserDAO
import com.example.postsapp.models.data.local.entities.PostEntity
import com.example.postsapp.models.data.local.entities.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun postDAO(): PostDAO
    abstract fun userDAO(): UserDAO
}