package com.example.carrillovictor_retrofit.network

import com.example.carrillovictor_retrofit.model.Comment
import com.example.carrillovictor_retrofit.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @GET("posts/{id}/comments")
    fun getCommentsByPostId(@Path("id") id: Int): Call<List<Comment>>
}
