package com.example.postsapp.models.data.source

import com.example.postsapp.models.data.remote.responses.PostResponse
import io.reactivex.Flowable

interface PostRemoteDataSource {

    fun refresh(): Flowable<List<PostResponse>>

    fun refreshByUser(userId: Int): Flowable<List<PostResponse>>
}