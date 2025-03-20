package com.tuyiiya.newsapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.tuyiiya.newsapp.MockData
import com.tuyiiya.newsapp.MockData.getTimeAgo
import com.tuyiiya.newsapp.R
import com.tuyiiya.newsapp.models.TopNewsArticle
import com.tuyiiya.newsapp.models.getAllArticleCategories
import com.tuyiiya.newsapp.network.NewsManager

@Composable
fun Categories(
    onFetchCategory: (String) -> Unit={},
    newsManager: NewsManager
) {
    val tabsItems = getAllArticleCategories()

    Column {
        LazyRow {
            items(tabsItems.size) {
                val category = tabsItems[it]

                CategoryTab(
                    category = category.categoryName,
                    onFetchCategory = onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category
                )
            }
        }

        ArticleContent(
            articles = newsManager.getArticleByCategory.value.articles ?: listOf()
        )
    }
}


@Composable
fun CategoryTab(
    category: String,
    isSelected: Boolean = false,
    onFetchCategory: (String) -> Unit
) {
    val background = if (isSelected) colorResource(id = R.color.purple_200) else colorResource(id = R.color.purple_700)

    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 16.dp)
            .clickable {
                onFetchCategory(category)
            },
        shape = MaterialTheme.shapes.small,
        color = background
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ArticleContent(
    articles: List<TopNewsArticle>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(articles) { article ->
            Card(
                modifier.padding(8.dp),
                border = BorderStroke(2.dp, color = colorResource(id = R.color.purple_500))
            ) {
                Row(
                    modifier.fillMaxWidth().padding(8.dp)
                ) {
                    CoilImage(
                        imageModel = article.urlToImage,
                        modifier = Modifier.size(100.dp),
                        placeHolder = painterResource(id = R.drawable.breaking_news),
                        error = painterResource(id = R.drawable.breaking_news)
                    )

                    Column(
                        modifier.padding(8.dp)
                    ) {
                        Text(
                            text = article.title ?: "Not Available",
                            fontWeight = FontWeight.Bold,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = article.author?.take(15)?.let {
                                    if (article.author.length > 15) "$it..." else it
                                } ?: "Not Available",
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = MockData.stringToDate(
                                    article.publishedAt ?: "2025-01-27T14:25:20Z"
                                ).getTimeAgo()
                            )
                        }
                    }
                }
            }
        }
    }
}
