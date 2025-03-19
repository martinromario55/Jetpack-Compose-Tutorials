package com.tuyiiya.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import com.tuyiiya.newsapp.R
import com.tuyiiya.newsapp.models.TopNewsArticle

@Composable
fun DetailScreen(
    //newsData: NewsData,
    article: TopNewsArticle,
    scrollState: ScrollState,
    navController: NavController
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                onBackPressed = { navController.popBackStack() }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(scrollState).padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Details Screen",
                fontWeight = FontWeight.SemiBold
            )

//            Image(
//                painter = painterResource(id = newsData.image),
//                contentDescription = ""
//            )
            CoilImage(
                imageModel = article.urlToImage,
                contentScale = ContentScale.Crop,
                error = ImageBitmap.imageResource(R.drawable.breaking_news),
                placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                InfoWithIcon(
                    Icons.Default.Edit, info = article.author?:"Not Availablee"
                )
                InfoWithIcon(
                    Icons.Default.DateRange, info = MockData.stringToDate(article.publishedAt!!).getTimeAgo()
                )
            }

            Text(text = article.title?:"Not Available", fontWeight = FontWeight.Bold)
            Text(text = article.description?:"Not Available", modifier = Modifier.padding(top = 16.dp))
        }
    }
}


@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(
            icon,
            contentDescription = "",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(id = R.color.purple_500)
        )

        Text(text = info)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    onBackPressed: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "Detail Screen",
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackPressed()}
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        TopNewsArticle(
            author = "Michael Schneider",
            title = "‘The Masked Singer’ Reveals Identity of the Beach Ball: Here Are the Stars Under the Mask - Variety",
            description = "SPOILER ALERT: Do not read ahead if you have not watched “The Masked Singer” Season 6, Episode 8, “Giving Thanks,” which aired November 3 on Fox. Honey Boo Boo, we hardly knew you. Reality TV mother and daughter stars June Edith “Mama June” Shannon and Alana …",
            publishedAt = "2021-11-04T02:00:00Z"
        ),
        rememberScrollState(),
        rememberNavController()
        )
}