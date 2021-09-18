package com.example.postsapp.mappers

interface BindMapper <in A, out B> {

    fun toBind() : B
    fun List<A>.toListBind() : List<B>
}