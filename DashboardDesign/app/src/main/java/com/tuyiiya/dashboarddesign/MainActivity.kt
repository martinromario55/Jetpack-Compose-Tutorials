package com.tuyiiya.dashboarddesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tuyiiya.dashboarddesign.ui.theme.DashGrey
import com.tuyiiya.dashboarddesign.ui.theme.DashRed
import com.tuyiiya.dashboarddesign.ui.theme.DashboardDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardDesignTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Dashboard()
                }
            }
        }
    }
}

@Composable
fun Dashboard() {
    Column(
        Modifier.fillMaxHeight().fillMaxWidth().background(color = DashGrey),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout {
            val (redBorder) = createRefs()
            Box(
                modifier = Modifier.fillMaxWidth().height(70.dp).constrainAs(redBorder) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                    .background(color = DashRed)
            )
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .height(60.dp)
                        .padding(start = 8.dp)
                        .weight(0.7f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Kikomando",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.userprofile),
                    contentDescription = "profile",
                    modifier = Modifier.width(50.dp).height(50.dp).clickable {  }
                )
            }
        }

        var searchText by rememberSaveable {
            mutableStateOf("")
        }
        TextField(
            value = searchText,
            onValueChange = {searchText = it},
            label = { Text(text = "Search restaurants...") },
            trailingIcon = {
                Image(painter = painterResource(id = R.drawable.search_icon), contentDescription = "Search icon",
                    modifier = Modifier.size(38.dp)
                        .padding(end = 6.dp)
                )
            },
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedPlaceholderColor = Color.White,
                unfocusedPlaceholderColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(
                    top = 24.dp,
                    end = 24.dp,
                    start = 24.dp
                )
                .shadow(
                    3.dp,
                    shape = RoundedCornerShape(50.dp)
                )
                .background(Color.White, CircleShape)
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 24.dp,
                    end = 24.dp,
                    start = 24.dp
                )
                .shadow(
                    3.dp,
                    shape = RoundedCornerShape(25.dp)
                )
                .background(DashRed)
                .height(150.dp)
        ) {
            val (bannerImage, flatText, freeText, couponText) = createRefs()
            Image(
                modifier = Modifier.constrainAs(bannerImage) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                painter = painterResource(id = R.drawable.bannerimg),
                contentDescription = "banner"
            )
            Text(
                text = "FLAT 50% OFF",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .constrainAs(flatText) {
                        top.linkTo(parent.top)
                        end.linkTo(bannerImage.start)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = "Free Delivery + 10% Cashback",
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(freeText) {
                        top.linkTo(flatText.bottom)
                        end.linkTo(flatText.end)
                        start.linkTo(flatText.start)
                    }
            )
            Text(
                text = "Coupon: FOOD50",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .constrainAs(couponText) {
                        top.linkTo(freeText.bottom)
                        end.linkTo(freeText.end)
                        start.linkTo(freeText.start)
                    }
            )
        }
        Text(
            text = "CATEGORIES",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 32.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp
                )
        ) {
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cake),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Cake",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pizza),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Pizza",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sandawiches),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Sandwich",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 24.dp
                )
        ) {
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.noodles),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Noodles",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pasta),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Pasta",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.biryani),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Biryani",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp
                )
        ) {
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.burger),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Burger",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icecream),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Ice Cream",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dalrice),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .padding(top = 8.dp, bottom = 4.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(5.dp)
                )
                Text(
                    text = "Dal Rice",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayDashboard() {
    DashboardDesignTheme {
        Dashboard()
    }
}