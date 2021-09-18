package com.example.postsapp.models.data.source

import com.example.postsapp.models.data.local.dao.UserDAO
import com.example.postsapp.models.data.local.entities.UserEntity
import com.example.postsapp.models.data.remote.api.UserApi
import com.example.postsapp.models.data.remote.responses.UserResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

class UserDataSource(private val userApi: UserApi, private val userDAO: UserDAO) : UserLocalDataSource, UserRemoteDataSource {

    override fun insertAll(users: List<UserEntity>): Completable = userDAO.insertAll(users)

    override fun insert(user: UserEntity): Completable = userDAO.insert(user)

    override fun update(user: UserEntity): Completable = userDAO.update(user)

    override fun refresh(): Flowable<List<UserResponse>> = userApi.getUsers()

    override fun getAll(): Maybe<List<UserEntity>> = userDAO.getAll()

    override fun getById(id: Long): Maybe<UserEntity> = userDAO.getById(id)

    override fun delete(user: UserEntity): Completable = userDAO.delete(user)

    override fun deleteById(id: Long): Completable = userDAO.deleteById(id)

    override fun deleteAll(): Completable = userDAO.deleteAll()
}