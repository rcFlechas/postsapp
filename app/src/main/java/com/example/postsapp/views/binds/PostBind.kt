package com.example.postsapp.views.binds

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostBind(
    val id: Long,
    val title: String,
    val body: String,
    val userId: Long
) : Parcelable
