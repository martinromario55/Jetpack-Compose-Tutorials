package com.tuyiiya.learnrcblayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.learnrcblayouts.ui.theme.LearnRCBLayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnRCBLayoutsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Column (
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = modifier
//        )
//        Text(
//            text = "Hello Java",
//            modifier = modifier
//        )
//    }

//    Row (
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = modifier
//        )
//        Text(
//            text = "Hello Java",
//            modifier = modifier
//        )
//    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.height(300.dp).width(300.dp).background(Color.Green)
        ) {
            Text(
            text = "Hello Java",
            modifier = modifier,
                color = Color.White,
                fontSize = 30.sp
                
        )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnRCBLayoutsTheme {
        Greeting("Android")
    }

}