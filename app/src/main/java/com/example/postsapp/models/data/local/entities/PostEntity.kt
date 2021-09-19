package com.example.postsapp.models.data.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.postsapp.mappers.BindMapper
import com.example.postsapp.views.binds.PostBind
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "post"
)
@Parcelize
data class PostEntity(

    @PrimaryKey
    @ColumnInfo(name = "post_id")
    val id: Long,

    @ColumnInfo(name = "post_title")
    val title: String,

    @ColumnInfo(name = "post_body")
    val body: String,

    @ColumnInfo(name = "user_id")
    val userId: Long
) : Parcelable, BindMapper<PostEntity, PostBind> {

    override fun toBind() = PostBind(
        id = id,
        title = title,
        body = body,
        userId = userId
    )
    override fun List<PostEntity>.toListBind() = map(PostEntity::toBind)
}
