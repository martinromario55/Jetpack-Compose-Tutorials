package com.tuyiiya.buttonimage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tuyiiya.buttonimage.ui.theme.ButtonImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonImageTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
//                    ButtonView()
                    ImageView()
                }
            }
        }
    }
}

@Composable
//fun ButtonView() {
//    val context = LocalContext.current.applicationContext
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Button(onClick = {
//            Toast.makeText(context, "Hello World",Toast.LENGTH_SHORT).show()
//        },
//            shape = RoundedCornerShape(size = 16.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
//            ) {
//            Text(text = "Button")
//        }
//    }
//}

fun ImageView() {
    Image(painter = painterResource(id = R.drawable.puppy), contentDescription = "puppy")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ButtonImageTheme {
    }
}