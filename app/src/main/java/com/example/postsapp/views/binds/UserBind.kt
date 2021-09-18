package com.example.postsapp.views.binds

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserBind(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String
) : Parcelable
