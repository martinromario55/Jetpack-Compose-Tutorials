package com.tuyiiya.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tuyiiya.newsapp.ui.MainViewModel
import com.tuyiiya.newsapp.ui.NewsApp
import com.tuyiiya.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        viewModel.getTopArticles()

        setContent {
            NewsAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsApp(viewModel)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NewsAppTheme {
//        NewsApp(viewModel())
//    }
//}