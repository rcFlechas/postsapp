package com.example.postsapp.models.data.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.postsapp.mappers.BindMapper
import com.example.postsapp.models.data.remote.responses.UserResponse
import com.example.postsapp.views.binds.UserBind
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "user",
    indices = [ Index( value = ["user_email"], unique = true)]
)
@Parcelize
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: Long,

    @ColumnInfo(name = "user_name")
    val name: String,

    @ColumnInfo(name = "user_email")
    val email: String,

    @ColumnInfo(name = "user_phone")
    val phone: String
) : Parcelable, BindMapper<UserEntity, UserBind> {

    override fun toBind() = UserBind(
        id = id,
        name = name,
        email = email,
        phone = phone
    )
    override fun List<UserEntity>.toListBind() = map(UserEntity::toBind)
}
