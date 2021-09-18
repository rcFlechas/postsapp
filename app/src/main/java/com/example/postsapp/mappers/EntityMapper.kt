package com.example.postsapp.mappers

interface EntityMapper <in A, out B> {

    fun toEntity() : B
    fun List<A>.toListEntity() : List<B>
}