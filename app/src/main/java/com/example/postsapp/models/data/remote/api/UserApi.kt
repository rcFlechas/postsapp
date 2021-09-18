package com.example.postsapp.models.data.remote.api

import com.example.postsapp.models.data.remote.responses.UserResponse
import com.example.postsapp.models.data.remote.rest.JsonPlaceHolder.GET_USERS
import io.reactivex.Flowable
import retrofit2.http.GET

interface UserApi {

    @GET(GET_USERS)
    fun getUsers(): Flowable<List<UserResponse>>
}