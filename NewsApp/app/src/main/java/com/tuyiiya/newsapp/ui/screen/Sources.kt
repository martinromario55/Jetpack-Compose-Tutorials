package com.tuyiiya.newsapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.tuyiiya.newsapp.network.NewsManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.tuyiiya.newsapp.models.TopNewsArticle
import com.tuyiiya.newsapp.R
import com.tuyiiya.newsapp.components.ErrorUI
import com.tuyiiya.newsapp.components.LoadingUI
import com.tuyiiya.newsapp.ui.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sources(
    //newsManager: NewsManager
    viewModel: MainViewModel,
    isLoading: MutableState<Boolean>,
    isError: MutableState<Boolean>
) {
    val items = listOf(
        "TechCrunch" to "techcrunch",
        "TalkSport" to "talksport",
        "Business Insider" to "business-insider",
        "Reuters" to "reuters",
        "Politico" to "politico",
        "TheVerge" to "the-verge",
    )


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "${viewModel.sourceName.collectAsState().value} Source")
                },
                actions = {
                    var menuExpanded by remember { mutableStateOf(false) }
                    IconButton(
                        onClick = { menuExpanded = true }
                    ) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = ""
                        )
                    }

                    MaterialTheme(
                        shapes = MaterialTheme.shapes.copy(
                            medium = RoundedCornerShape(16.dp)
                        )
                    ) {
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            items.forEach {
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.sourceName.value = it.second
                                        viewModel.getArticleBySource()
                                        menuExpanded = false
                                    },
                                    text = { Text(it.first) }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) {
        when {
            isLoading.value -> LoadingUI()
            isError.value -> ErrorUI()
            else -> {
                viewModel.getArticleBySource()
                val articles = viewModel.getArticleBySource.collectAsState().value
                SourceContent(articles = articles.articles?: listOf())
            }
        }
    }
}


@Composable
fun SourceContent(articles: List<TopNewsArticle>) {
    val uriHandler = LocalUriHandler.current


    LazyColumn(
        modifier = Modifier.padding(top = 80.dp)
    ) {
        items(articles) { article ->
            val annotatedString = buildAnnotatedString {
                pushStringAnnotation(
                    tag = "URL",
                    annotation = article.url ?: "newsapi.org"
                )

                withStyle(
                    style = SpanStyle(color = colorResource(id = R.color.purple_500),
                        textDecoration = TextDecoration.Underline
                        )
                ) {
                    append("Read Full Article Here")
                }
            }

            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Blue),
                modifier = Modifier.padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.height(200.dp).padding(end = 8.dp, start = 8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = article.title ?: "Not Available",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = article.description ?: "Not Available",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                    ) {
                        ClickableText(
                            text = annotatedString,
                            onClick = {
                                annotatedString.getStringAnnotations(it,it).firstOrNull()?.let { result ->
                                    if (result.tag == "URL") {
                                        uriHandler.openUri(result.item)
                                    }
                                }
                            },
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}