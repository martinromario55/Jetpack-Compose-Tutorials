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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
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
import com.tuyiiya.newsapp.network.Api
import com.tuyiiya.newsapp.network.NewsManager
import com.tuyiiya.newsapp.ui.screen.Categories
import com.tuyiiya.newsapp.ui.screen.DetailScreen
import com.tuyiiya.newsapp.ui.screen.Sources
import com.tuyiiya.newsapp.ui.screen.TopNews
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun NewsApp(
    mainViewModel: MainViewModel
) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()

    MainScreen(
        navController = navController,
        scrollState = scrollState,
        mainViewModel = mainViewModel
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    scrollState: ScrollState,
    mainViewModel: MainViewModel
){
    Scaffold(
        bottomBar = {
            BottomMenu(navController)
        }
    ) {
        Navigation(
            navController = navController,
            scrollState = scrollState,
            paddingValues = it,
            viewModel = mainViewModel
        )
    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    newsManager: NewsManager = NewsManager(Api.retrofitService),
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
//    val articles = newsManager.newsResponse.value.articles ?: emptyList()
    val articles = mutableListOf(TopNewsArticle())

    val topArticles = viewModel.newsResponse.collectAsState().value.articles

    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.isError.collectAsState()

    //articles.addAll(newsManager.newsResponse.value.articles ?: listOf(TopNewsArticle()))
    articles.addAll(topArticles ?: listOf())
    //Log.d("News", "$articles")

    articles?.let {
        NavHost(
            navController = navController,
            //startDestination = "TopNews",
            startDestination = BottomMenuScreen.TopNews.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            val queryState = mutableStateOf(viewModel.query.value)
            val isLoading = mutableStateOf(loading)
            val isError = mutableStateOf(error)

            bottomNavigation(
                navController = navController,
                articles,
                //newsManager,
                query = queryState,
                viewModel,
                isLoading = isLoading,
                isError = isError
            )

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

                    if (queryState.value != "") {
                        articles.clear()
                        articles.addAll(viewModel.searchedNewsResponse.value.articles ?: listOf())

                    } else {
                        articles.clear()
                        articles.addAll(
                            //newsManager.newsResponse.value.articles ?: listOf()
                            viewModel.newsResponse.value.articles ?: listOf()
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
    //newsManager: NewsManager,
    query: MutableState<String>,
    viewModel: MainViewModel,
    isLoading: MutableState<Boolean>,
    isError: MutableState<Boolean>
) {
     composable(
         BottomMenuScreen.TopNews.route
     ) {
         if (articles.isEmpty()) {
             Text("No articles available") // Prevent crashes when articles are missing
         } else {
             TopNews(
                 navController = navController,
                 articles,
                 query,
                 viewModel = viewModel,
                 isLoading = isLoading,
                 isError = isError
             )
         }
     }

    composable(
        BottomMenuScreen.Categories.route
    ) {
        //newsManager.getArticlesByCategory("business")
        //newsManager.getArticlesByCategory("business")

        viewModel.getArticlesByCategory("business")

        viewModel.onSelectedCategoryChanged("business")

        Categories(
            // newsManager = newsManager,
            viewModel = viewModel,
            onFetchCategory = {
                viewModel.onSelectedCategoryChanged(it)
                viewModel.getArticlesByCategory(it)
            },
            isLoading = isLoading,
            isError = isError
        )
    }

    composable(
        BottomMenuScreen.Sources.route
    ) {
        Sources(
            //newsManager = newsManager
            viewModel = viewModel,
            isLoading = isLoading,
            isError = isError
        )
    }
}