package com.example.postsapp.models.data.remote.responses

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.postsapp.mappers.EntityMapper
import com.example.postsapp.models.data.local.entities.PostEntity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class PostResponse(

    @Json(name = "userId")
    @field:Json(name = "userId")
    val userId: Int,

    @Json(name = "id")
    @field:Json(name = "id")
    val postId: Int,

    @Json(name = "title")
    @field:Json(name = "title")
    val title: String,

    @Json(name = "body")
    @field:Json(name = "body")
    val body: String
) : Parcelable, EntityMapper<PostResponse, PostEntity> {

    override fun toEntity() = PostEntity (
        id = postId.toLong(),
        title = title,
        body = body,
        userId = userId.toLong()
    )
    override fun List<PostResponse>.toListEntity() = map(PostResponse::toEntity)
}