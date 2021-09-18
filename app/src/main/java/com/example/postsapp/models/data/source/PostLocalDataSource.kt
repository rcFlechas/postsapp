package com.example.postsapp.models.data.source

import com.example.postsapp.models.data.local.entities.PostEntity
import io.reactivex.Completable
import io.reactivex.Maybe

interface PostLocalDataSource {

    fun insertAll(posts: List<PostEntity>): Completable

    fun insert(post: PostEntity): Completable

    fun update(post: PostEntity): Completable

    fun getAll(): Maybe<List<PostEntity>>

    fun getById(id: Long): Maybe<PostEntity>

    fun delete(post: PostEntity): Completable

    fun deleteById(id: Long): Completable

    fun deleteAll(): Completable
}