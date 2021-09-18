package com.example.postsapp.models.repositories.impl

import com.example.postsapp.models.data.local.entities.PostEntity
import com.example.postsapp.models.data.remote.responses.PostResponse
import com.example.postsapp.models.data.source.PostLocalDataSource
import com.example.postsapp.models.data.source.PostRemoteDataSource
import com.example.postsapp.models.repositories.PostRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

class PostRepositoryImpl (
    private val postLocalDataSource: PostLocalDataSource,
    private val postRemoteDataSource: PostRemoteDataSource
): PostRepository {

    override fun refresh(): Flowable<List<PostResponse>> = postRemoteDataSource.refresh()

    override fun refreshByUser(userId: Int): Flowable<List<PostResponse>> = postRemoteDataSource.refreshByUser(userId)

    override fun insertAll(posts: List<PostEntity>): Completable = postLocalDataSource.insertAll(posts)

    override fun insert(post: PostEntity): Completable = postLocalDataSource.insert(post)

    override fun update(post: PostEntity): Completable = postLocalDataSource.update(post)

    override fun getAll(): Maybe<List<PostEntity>> = postLocalDataSource.getAll()

    override fun getById(id: Long): Maybe<PostEntity> = postLocalDataSource.getById(id)

    override fun delete(post: PostEntity): Completable = postLocalDataSource.delete(post)

    override fun deleteById(id: Long): Completable = postLocalDataSource.deleteById(id)

    override fun deleteAll(): Completable = postLocalDataSource.deleteAll()
}