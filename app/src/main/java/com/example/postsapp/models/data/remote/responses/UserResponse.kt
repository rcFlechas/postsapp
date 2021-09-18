package com.example.postsapp.models.data.remote.responses

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.postsapp.mappers.BindMapper
import com.example.postsapp.mappers.EntityMapper
import com.example.postsapp.models.data.local.entities.UserEntity
import com.example.postsapp.views.binds.UserBind
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class UserResponse(
    @Json(name = "id")
    @field:Json(name = "id")
    val id: Long,

    @Json(name = "name")
    @field:Json(name = "name")
    val name: String,

    @Json(name = "username")
    @field:Json(name = "username")
    val nickName: String,

    @Json(name = "email")
    @field:Json(name = "email")
    val email: String,

    @Json(name = "address")
    @field:Json(name = "address")
    val address: Address,

    @Json(name = "phone")
    @field:Json(name = "phone")
    val phone: String,

    @Json(name = "website")
    @field:Json(name = "website")
    val webSite: String,

    @Json(name = "company")
    @field:Json(name = "company")
    val company: Company

) : Parcelable, BindMapper<UserResponse, UserBind>, EntityMapper<UserResponse, UserEntity> {

    override fun toBind() = UserBind(
        id = id,
        name = name,
        email = email,
        phone = phone
    )
    override fun List<UserResponse>.toListBind() = map(UserResponse::toBind)

    override fun toEntity() = UserEntity(
        id = id,
        name = name,
        email = email,
        phone = phone
    )
    override fun List<UserResponse>.toListEntity() = map(UserResponse::toEntity)
}

@Parcelize
data class Address(

    @Json(name = "street")
    @field:Json(name = "street")
    val street: String,

    @Json(name = "suite")
    @field:Json(name = "suite")
    val suite: String,

    @Json(name = "city")
    @field:Json(name = "city")
    val city: String,

    @Json(name = "zipcode")
    @field:Json(name = "zipcode")
    val zipCode: String,

    @Json(name = "geo")
    @field:Json(name = "geo")
    val geo: Geo
) : Parcelable

@Parcelize
data class Geo(

    @Json(name = "lat")
    @field:Json(name = "lat")
    val lat: String,

    @Json(name = "lng")
    @field:Json(name = "lng")
    val lng: String

) : Parcelable

@Parcelize
data class Company(

    @Json(name = "name")
    @field:Json(name = "name")
    val name: String,

    @Json(name = "catchPhrase")
    @field:Json(name = "catchPhrase")
    val catchPhrase: String,

    @Json(name = "bs")
    @field:Json(name = "bs")
    val bs: String

) : Parcelable