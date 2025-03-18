package com.tuyiiya.newsapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
            scrollState = scrollState
        )
    }
}


@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState) {

    NavHost(
        navController = navController,
        startDestination = "TopNews"
    ) {
        bottomNavigation(navController = navController)

        composable("TopNews") { TopNews(navController = navController) }
        composable(
            "Detail/{newsId}",
            arguments = listOf(navArgument("newsId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("newsId")
            val newsData = MockData.getNews(id)
            DetailScreen(newsData, scrollState, navController)
        }
    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController) {
     composable(
         BottomMenuScreen.TopNews.route
     ) {
         TopNews(navController = navController)
     }

    composable(
        BottomMenuScreen.Categories.route
    ) {
        Categories()
    }

    composable(
        BottomMenuScreen.Sources.route
    ) {
        Sources()
    }
}