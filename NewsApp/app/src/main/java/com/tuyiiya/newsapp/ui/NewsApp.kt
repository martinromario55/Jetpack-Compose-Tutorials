package com.tuyiiya.newsapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tuyiiya.newsapp.BottomMenuScreen
import com.tuyiiya.newsapp.MockData
import com.tuyiiya.newsapp.components.BottomMenu
import com.tuyiiya.newsapp.models.TopNewsArticle
import com.tuyiiya.newsapp.network.NewsManager
import com.tuyiiya.newsapp.ui.screen.Categories
import com.tuyiiya.newsapp.ui.screen.DetailScreen
import com.tuyiiya.newsapp.ui.screen.Sources
import com.tuyiiya.newsapp.ui.screen.TopNews

@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()

    MainScreen(navController = navController, scrollState = scrollState)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    scrollState: ScrollState
){
    Scaffold(
        bottomBar = {
            BottomMenu(navController)
        }
    ) {
        Navigation(
            navController = navController,
            scrollState = scrollState,
            paddingValues = it
        )
    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    newsManager: NewsManager = NewsManager(),
    paddingValues: PaddingValues
) {
//    val articles = newsManager.newsResponse.value.articles ?: emptyList()
    val articles = mutableListOf(TopNewsArticle())
    articles.addAll(newsManager.newsResponse.value.articles ?: listOf(TopNewsArticle()))
    //Log.d("News", "$articles")

    articles?.let {
        NavHost(
            navController = navController,
            //startDestination = "TopNews",
            startDestination = BottomMenuScreen.TopNews.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            bottomNavigation(navController = navController, articles, newsManager)

            //composable("TopNews") { TopNews(navController = navController, articles) }
            composable(
                "Detail/{index}",
                arguments = listOf(navArgument("index") { type = NavType.IntType })
            ) { navBackStackEntry ->
                //val id = navBackStackEntry.arguments?.getInt("index")
                val index = navBackStackEntry.arguments?.getInt("index")
                //val newsData = MockData.getNews(id)
                //DetailScreen(newsData, scrollState, navController)
                index?.let {
                    val article = articles[index]

                    if (newsManager.query.value.isNotEmpty()) {
                        articles.clear()
                        articles.addAll(newsManager.searchedNewsResponse.value.articles ?: listOf( ))

                    } else {
                        articles.clear()
                        articles.addAll(
                            newsManager.newsResponse.value.articles ?: listOf()
                        )
                    }

                    DetailScreen(article, scrollState, navController)
                }
            }
        }
    }
}

fun NavGraphBuilder.bottomNavigation(
    navController: NavController,
    articles: List<TopNewsArticle>,
    newsManager: NewsManager
) {
     composable(
         BottomMenuScreen.TopNews.route
     ) {
         if (articles.isEmpty()) {
             Text("No articles available") // Prevent crashes when articles are missing
         } else {
             TopNews(navController = navController, articles, newsManager.query, newsManager = newsManager)
         }
     }

    composable(
        BottomMenuScreen.Categories.route
    ) {
        newsManager.getArticlesByCategory("business")
        newsManager.getArticlesByCategory("business")

        Categories(
            newsManager = newsManager,
            onFetchCategory = {
                newsManager.onSelectedCategoryChanged(it)
                newsManager.getArticlesByCategory(it)
            }
        )
    }

    composable(
        BottomMenuScreen.Sources.route
    ) {
        Sources(
            newsManager = newsManager
        )
    }
}