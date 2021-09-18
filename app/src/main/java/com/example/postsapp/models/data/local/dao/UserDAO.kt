package com.example.postsapp.models.data.local.dao

import androidx.room.*
import com.example.postsapp.models.data.local.entities.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(users: List<UserEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserEntity): Completable

    @Update
    fun update(user: UserEntity): Completable

    @Query("SELECT * FROM user")
    fun getAll(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM user WHERE user_id = :id")
    fun getById(id: Long): Maybe<UserEntity>

    @Delete
    fun delete(user: UserEntity): Completable

    @Query("DELETE FROM user WHERE user_id = :id")
    fun deleteById(id: Long): Completable

    @Query("DELETE FROM user")
    fun deleteAll(): Completable
}