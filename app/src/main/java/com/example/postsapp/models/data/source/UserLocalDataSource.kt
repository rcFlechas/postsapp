package com.example.postsapp.models.data.source

import com.example.postsapp.models.data.local.entities.UserEntity
import io.reactivex.Completable
import io.reactivex.Maybe

interface UserLocalDataSource {

    fun insertAll(users: List<UserEntity>): Completable

    fun insert(user: UserEntity): Completable

    fun update(user: UserEntity): Completable

    fun getAll(): Maybe<List<UserEntity>>

    fun getById(id: Long): Maybe<UserEntity>

    fun delete(user: UserEntity): Completable

    fun deleteById(id: Long): Completable

    fun deleteAll(): Completable
}