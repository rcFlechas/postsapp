package com.example.postsapp.models.repositories.impl

import com.example.postsapp.models.data.local.entities.UserEntity
import com.example.postsapp.models.data.remote.responses.UserResponse
import com.example.postsapp.models.data.source.UserLocalDataSource
import com.example.postsapp.models.data.source.UserRemoteDataSource
import com.example.postsapp.models.repositories.UserRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun refresh(): Flowable<List<UserResponse>> = userRemoteDataSource.refresh()

    override fun insertAll(users: List<UserEntity>): Completable = userLocalDataSource.insertAll(users)

    override fun insert(user: UserEntity): Completable = userLocalDataSource.insert(user)

    override fun update(user: UserEntity): Completable = userLocalDataSource.update(user)

    override fun getAll(): Flowable<List<UserEntity>> = userLocalDataSource.getAll()

    override fun getById(id: Long): Maybe<UserEntity> = userLocalDataSource.getById(id)

    override fun delete(user: UserEntity): Completable = userLocalDataSource.delete(user)

    override fun deleteById(id: Long): Completable = userLocalDataSource.deleteById(id)

    override fun deleteAll(): Completable = userLocalDataSource.deleteAll()
}