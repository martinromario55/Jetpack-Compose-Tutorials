package com.tuyiiya.lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.lazylist.ui.theme.LazyListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyListTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    val fruitsList = listOf("Papaya", "Watermelon", "Banana", "Strawberry", "Apple","Papaya", "Watermelon", "Banana", "Strawberry", "Apple","Papaya", "Watermelon", "Banana", "Strawberry", "Apple","Papaya", "Watermelon", "Banana", "Strawberry", "Apple","Papaya", "Watermelon", "Banana", "Strawberry", "Apple","Papaya", "Watermelon", "Banana", "Strawberry", "Apple")
                    DisplayList(fruitsList)
                }
            }
        }
    }
}

@Composable
fun DisplayList(fruitList: List<String>) {
//    Column {
//        fruitList.forEach{
//            Text(text = " $it", fontSize = 30.sp, color = Color.Red)
//        }
//    }

//    LazyColumn {
//        items(fruitList) {
//            Text(text = " $it", fontSize = 30.sp, color = Color.Red)
//        }
//    }

    LazyRow {
        items(fruitList) {
            Text(text = " $it", fontSize = 30.sp, color = Color.Red)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyListTheme {

    }
}