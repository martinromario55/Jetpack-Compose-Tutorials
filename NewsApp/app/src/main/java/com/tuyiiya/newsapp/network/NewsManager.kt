package com.tuyiiya.newsapp.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.tuyiiya.newsapp.models.ArticleCategory
import com.tuyiiya.newsapp.models.TopNewsResponse
import com.tuyiiya.newsapp.models.getArticleCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager(
    private val service: NewsService
) {
    private val _newsResponse = mutableStateOf(TopNewsResponse())

    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember {
            _newsResponse
        }

    val sourceName = mutableStateOf("abc-news")

//    private val _getArticleByCategory = mutableStateOf(TopNewsResponse())
//    val getArticleByCategory: MutableState<TopNewsResponse>
//        @Composable get() = remember {
//            _getArticleByCategory
//        }

    private val _getArticleBySource = mutableStateOf(TopNewsResponse())
    val getArticleBySource: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _getArticleBySource
        }

    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(null)

    val query = mutableStateOf("")

    private val _searchedNewsResponse = mutableStateOf(TopNewsResponse())
    val searchedNewsResponse: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _searchedNewsResponse
        }

//    init {
//        getArticles()
//    }


    suspend fun getArticles(country: String): TopNewsResponse = withContext(Dispatchers.IO) {
        service.getTopArticles(country = country, Api.API_KEY)
    }


    suspend fun getArticlesByCategory(category: String): TopNewsResponse = withContext(Dispatchers.IO) {
        service.getArticlesByCategory(category = category, Api.API_KEY)
    }


    suspend fun getArticlesBySource(source: String): TopNewsResponse = withContext(Dispatchers.IO) {
        service.getArticlesBySources(source, Api.API_KEY)
    }


    suspend fun getSearchedArticles(query: String): TopNewsResponse = withContext(Dispatchers.IO) {
        service.getArticles(query, Api.API_KEY)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getArticleCategory(category = category)

        selectedCategory.value = newCategory
    }
}