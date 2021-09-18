package com.example.postsapp.mappers

interface RequestMapper <in A, out B> {

    fun toRequest() : B
    fun List<A>.toListRequest() : List<B>
}