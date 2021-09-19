package com.example.postsapp.models.data.source

import com.example.postsapp.models.data.local.dao.PostDAO
import com.example.postsapp.models.data.local.entities.PostEntity
import com.example.postsapp.models.data.remote.api.PostApi
import com.example.postsapp.models.data.remote.responses.PostResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

class PostDataSource(private val postApi: PostApi, private val postDAO: PostDAO) : PostLocalDataSource, PostRemoteDataSource {

    override fun insertAll(posts: List<PostEntity>): Completable = postDAO.insertAll(posts)

    override fun insert(post: PostEntity): Completable = postDAO.insert(post)

    override fun update(post: PostEntity): Completable = postDAO.update(post)

    override fun getAll(): Maybe<List<PostEntity>> = postDAO.getAll()

    override fun getById(id: Long): Maybe<PostEntity> = postDAO.getById(id)

    override fun getByUser(userId: Long): Flowable<List<PostEntity>> = postDAO.getByUser(userId)

    override fun delete(post: PostEntity): Completable = postDAO.delete(post)

    override fun deleteById(id: Long): Completable = postDAO.deleteById(id)

    override fun deleteAll(): Completable = postDAO.deleteAll()

    override fun refresh(): Flowable<List<PostResponse>> = postApi.getPosts()

    override fun refreshByUser(userId: Int): Flowable<List<PostResponse>> = postApi.getPostByUser(userId)
}