package com.tuyiiya.card

import androidx.annotation.DrawableRes

data class YoutubeData(
    @DrawableRes val thumbnail: Int,
    val videoTitle: String,
    val channelName: String
)
