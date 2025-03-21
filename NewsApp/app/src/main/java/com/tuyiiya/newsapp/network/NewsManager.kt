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

    private val _getArticleByCategory = mutableStateOf(TopNewsResponse())
    val getArticleByCategory: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _getArticleByCategory
        }

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

    init {
        getArticles()
    }


    suspend fun getArticles(country: String): TopNewsResponse = withContext(Dispatchers.IO) {
//        val service = Api.retrofitService.getTopArticles("us", Api.API_KEY)
//
//        service.enqueue(object: Callback<TopNewsResponse> {
//            override fun onResponse(
//                call: Call<TopNewsResponse?>,
//                response: Response<TopNewsResponse?>
//            ) {
//                if (response.isSuccessful) {
//                    _newsResponse.value = response.body()!!
//
//                    Log.d("News", "${_newsResponse.value}")
//                } else {
//                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
//                    Log.d("error", "Response Code: ${response.code()}, Error: $errorBody")
//                }
//            }
//
//            override fun onFailure(
//                call: Call<TopNewsResponse?>,
//                t: Throwable
//            ) {
//                Log.d("error", "${t.printStackTrace()}")
//            }
//
//        })
        service.getTopArticles(country = country, Api.API_KEY)
    }


    fun getArticlesByCategory(category: String) {
        val service = Api.retrofitService.getArticlesByCategory(category, Api.API_KEY)

        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse?>,
                response: Response<TopNewsResponse?>
            ) {
                if (response.isSuccessful) {
                    _getArticleByCategory.value = response.body()!!

                    Log.d("Category", "${_getArticleByCategory.value}")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.d("error", "Response Code: ${response.code()}, Error: $errorBody")
                }
            }

            override fun onFailure(
                call: Call<TopNewsResponse?>,
                t: Throwable
            ) {
                Log.d("error", "${t.printStackTrace()}")
            }

        })
    }


    fun getArticlesBySource() {
        val service = Api.retrofitService.getArticlesBySources(sourceName.value, Api.API_KEY)

        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse?>,
                response: Response<TopNewsResponse?>
            ) {
                if (response.isSuccessful) {
                    _getArticleBySource.value = response.body()!!

                    Log.d("Source", "${_getArticleBySource.value}")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.d("error", "Response Code: ${response.code()}, Error: $errorBody")
                }
            }

            override fun onFailure(
                call: Call<TopNewsResponse?>,
                t: Throwable
            ) {
                Log.d("error", "${t.printStackTrace()}")
            }

        })
    }


    fun getSearchedArticles(query: String) {
        val service = Api.retrofitService.getArticles(query, Api.API_KEY)

        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse?>,
                response: Response<TopNewsResponse?>
            ) {
                if (response.isSuccessful) {
                    _searchedNewsResponse.value = response.body()!!

                    Log.d("Search", "${_searchedNewsResponse.value}")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.d("searchError", "Response Code: ${response.code()}, Error: $errorBody")
                }
            }

            override fun onFailure(
                call: Call<TopNewsResponse?>,
                t: Throwable
            ) {
                Log.d("searchError", "${t.printStackTrace()}")
            }

        })
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getArticleCategory(category = category)

        selectedCategory.value = newCategory
    }
}