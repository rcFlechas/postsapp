package com.example.postsapp.mappers

interface ResponseMapper <in A, out B> {

    fun toResponse() : B
    fun List<A>.toListResponse() : List<B>
}