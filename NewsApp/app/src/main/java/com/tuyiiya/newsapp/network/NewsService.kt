package com.tuyiiya.newsapp.network

import com.tuyiiya.newsapp.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getTopArticles(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): TopNewsResponse


    @GET("top-headlines")
    suspend fun getArticlesByCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): TopNewsResponse


    @GET("everything")
    suspend fun getArticlesBySources(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): TopNewsResponse


    @GET("everything")
    suspend fun getArticles(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): TopNewsResponse
}