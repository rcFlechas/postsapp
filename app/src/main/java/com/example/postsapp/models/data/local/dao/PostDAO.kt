package com.example.postsapp.models.data.local.dao

import androidx.room.*
import com.example.postsapp.models.data.local.entities.PostEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface PostDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(posts: List<PostEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(post: PostEntity): Completable

    @Update
    fun update(post: PostEntity): Completable

    @Query("SELECT * FROM post")
    fun getAll(): Maybe<List<PostEntity>>

    @Query("SELECT * FROM post WHERE post_id = :id")
    fun getById(id: Long): Maybe<PostEntity>

    @Query("SELECT * FROM post WHERE user_id = :userId")
    fun getByUser(userId: Long): Flowable<List<PostEntity>>

    @Delete
    fun delete(post: PostEntity): Completable

    @Query("DELETE FROM post WHERE post_id = :id")
    fun deleteById(id: Long): Completable

    @Query("DELETE FROM post")
    fun deleteAll(): Completable
}