package com.tuyiiya.dogprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun ProfilePage() {
    Card(
//        elevation = 6.dp,
        modifier = Modifier.fillMaxSize()
            .padding(
                top = 100.dp,
                bottom = 100.dp,
                start = 14.dp,
                end = 16.dp
            )
            .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(30.dp))
    ) {
        // Content of our Card - Including Dog Image, Description, followers, etc.
        ConstraintLayout {
            val (
                image,
                nameText,
                countryText,
                rowStats,
                buttonFollow,
                buttonMessage
            ) = createRefs()

            val guideLine = createGuidelineFromTop(0.1f)


            Image(painter = painterResource(id = R.drawable.husky)
                , contentDescription = "husky",
                modifier = Modifier.size(200.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = CircleShape
                    ).constrainAs(image) {
                        //top.linkTo(parent.top)
                        top.linkTo(guideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )

            Text(text = "Siberian Husky",
                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Text(text = "Germany",
                modifier = Modifier.constrainAs(countryText) {
                    top.linkTo(nameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(16.dp).constrainAs(rowStats) {
                    top.linkTo(countryText.bottom)
                }
            ) {
                ProfileStats("830", "Followers")
                ProfileStats("120", "Following")
                ProfileStats("330", "Posts")
            }

            Button(
                onClick = {},
                modifier = Modifier.constrainAs(buttonFollow) {
                    top.linkTo(rowStats.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(buttonMessage.start)
                    width = Dimension.wrapContent
                }
            ) {
                Text(text = "Follow User")
            }
            Button(
                onClick = {},
                modifier = Modifier.constrainAs(buttonMessage) {
                    top.linkTo(rowStats.bottom, margin = 16.dp)
                    start.linkTo(buttonFollow.end)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                }
            ) {
                Text(text = "Direct Message")
            }
        }
    }
}

@Composable
fun ProfileStats (count: String, title:String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}