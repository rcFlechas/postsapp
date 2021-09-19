package com.example.postsapp.models.data.remote.api

import com.example.postsapp.models.data.remote.responses.PostResponse
import com.example.postsapp.models.data.remote.rest.JsonPlaceHolder.GET_POSTS
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET(GET_POSTS)
    fun getPosts(): Flowable<List<PostResponse>>

    @GET(GET_POSTS)
    fun getPostByUser(@Query("userId") userId: Int): Flowable<List<PostResponse>>
}