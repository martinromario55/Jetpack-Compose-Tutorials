package com.tuyiiya.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.card.ui.theme.CardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            CardTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayYouTubeVideos()
                }
            }
        }
    }
}

@Composable
fun DisplayYouTubeVideos () {
    val videosList = listOf(
        YoutubeData(R.drawable.thumbone, "Coding with Snoop", "Snoop Code Channel"),
        YoutubeData(R.drawable.thumbtwo, "Learn Android Studio", "Android Tutorials"),
        YoutubeData(R.drawable.thumbone, "Latest News on AI", "The AI Post"),
        YoutubeData(R.drawable.thumbone, "Top Trending Programming Languages", "Tech News")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(videosList) {
            video -> YouTubeUI(youtubeData = video)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CardTheme {
        DisplayYouTubeVideos()
    }
}