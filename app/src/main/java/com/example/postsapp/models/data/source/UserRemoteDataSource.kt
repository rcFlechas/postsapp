package com.example.postsapp.models.data.source

import com.example.postsapp.models.data.remote.responses.UserResponse
import io.reactivex.Flowable

interface UserRemoteDataSource {

    fun refresh(): Flowable<List<UserResponse>>
}