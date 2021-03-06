package com.example.postsapp.models.repositories

import com.example.postsapp.models.data.local.entities.PostEntity
import com.example.postsapp.models.data.remote.responses.PostResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

interface PostRepository {

    fun refresh(): Flowable<List<PostResponse>>

    fun refreshByUser(userId: Int): Flowable<List<PostResponse>>

    fun insertAll(posts: List<PostEntity>): Completable

    fun insert(post: PostEntity): Completable

    fun update(post: PostEntity): Completable

    fun getAll(): Maybe<List<PostEntity>>

    fun getById(id: Long): Maybe<PostEntity>

    fun getByUser(userId: Long): Flowable<List<PostEntity>>

    fun delete(post: PostEntity): Completable

    fun deleteById(id: Long): Completable

    fun deleteAll(): Completable
}