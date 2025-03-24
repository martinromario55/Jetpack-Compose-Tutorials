package com.tuyiiya.newsapp

import android.app.Application
import com.tuyiiya.newsapp.data.Repository
import com.tuyiiya.newsapp.network.Api
import com.tuyiiya.newsapp.network.NewsManager

class MainApp: Application() {
    private val manager by lazy {
        NewsManager(Api.retrofitService)
    }

    val repository by lazy {
        Repository(manager = manager)
    }
}