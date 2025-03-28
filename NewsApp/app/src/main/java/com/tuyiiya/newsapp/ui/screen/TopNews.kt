package com.tuyiiya.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.coil.CoilImage
import com.tuyiiya.newsapp.MockData
import com.tuyiiya.newsapp.MockData.getTimeAgo
import com.tuyiiya.newsapp.NewsData
import com.tuyiiya.newsapp.models.TopNewsArticle
import com.tuyiiya.newsapp.ui.Navigation
import com.tuyiiya.newsapp.R
import com.tuyiiya.newsapp.components.ErrorUI
import com.tuyiiya.newsapp.components.LoadingUI
import com.tuyiiya.newsapp.components.SearchBar
import com.tuyiiya.newsapp.network.NewsManager
import com.tuyiiya.newsapp.ui.MainViewModel

@Composable
fun TopNews(
    navController: NavController,
    articles: List<TopNewsArticle>,
    query: MutableState<String>,
    //newsManager: NewsManager
    viewModel: MainViewModel,
    isLoading: MutableState<Boolean>,
    isError: MutableState<Boolean>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Text(
//            text = "Top News",
//            fontWeight = FontWeight.SemiBold
//        )
        SearchBar(query = query, viewModel = viewModel)

        val searchedText = query.value
        val resultList = mutableListOf<TopNewsArticle>()

        if (searchedText != "") {
            resultList.addAll(
                viewModel.searchedNewsResponse.collectAsState().value.articles?: articles
            )
        } else {
            resultList.addAll(articles)
        }

//        LazyColumn {
//            items(MockData.topNewsList) { newsData ->
//                TopNewsItem(
//                    newsData = newsData,
//                    onNewsClick = {
//                        navController.navigate("Detail/${newsData.id}")
//                    }
//                )
//            }
//        }

        when {
            isLoading.value -> LoadingUI()
            isError.value -> ErrorUI()
            else -> {
                LazyColumn {
                    items(resultList.size) { index ->
                        TopNewsItem(
                            article = resultList[index],
                            onNewsClick = {
                                navController.navigate("Detail/$index")
                            }
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun TopNewsItem(
    //newsData: NewsData,
    article: TopNewsArticle,
    onNewsClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onNewsClick()
            }
    ) {
//        Image(
//            painter = painterResource(id = newsData.image),
//            contentDescription = "",
//            contentScale = ContentScale.FillBounds
//        )
        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news)
        )
        Column(
            modifier = Modifier.wrapContentHeight()
                .padding(top = 16.dp, start = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            article.publishedAt?.let {
                Text(
                    text = MockData.stringToDate(article.publishedAt).getTimeAgo(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            article.title?.let {
                Text(
                    text = article.title,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TopNewsPreview() {
//    TopNews(rememberNavController())
//}