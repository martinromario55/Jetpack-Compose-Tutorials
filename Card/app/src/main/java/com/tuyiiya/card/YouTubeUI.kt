package com.tuyiiya.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.card.ui.theme.CardGreen

@Composable
fun YouTubeUI (youtubeData: YoutubeData) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            Modifier.background(CardGreen)
        ) {
            Image(
                painter = painterResource(id = youtubeData.thumbnail),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().aspectRatio(16f/9f),
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier.fillMaxWidth().padding(18.dp)
            ) {
                Text(
                    text = youtubeData.videoTitle,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = youtubeData.channelName,
                    maxLines = 1,
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }
        }
    }
}