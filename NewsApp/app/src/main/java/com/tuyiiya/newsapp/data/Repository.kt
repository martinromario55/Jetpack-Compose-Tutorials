package com.tuyiiya.newsapp.data

import com.tuyiiya.newsapp.network.NewsManager

class Repository(val manager: NewsManager) {
    suspend fun getArticles() = manager.getArticles(("us"))

    suspend fun getArticlesByCategory(category: String) = manager.getArticlesByCategory(category)

    suspend fun getArticlesBySource(source: String) = manager.getArticlesBySource(source = source)

    suspend fun getSearchedArticles(query: String) = manager.getSearchedArticles(query = query)
}