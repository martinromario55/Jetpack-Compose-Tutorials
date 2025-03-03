package com.tuyiiya.learntextmodifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.tuyiiya.learntextmodifiers.ui.theme.LearnTextModifiersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnTextModifiersTheme {
                Scaffold { innerPadding ->
                    LearnTextAndModifiers(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LearnTextAndModifiers(modifier: Modifier = Modifier ) {
    Text(
//        text = "Hello World",
        text = stringResource(R.string.hello_string),
        modifier = Modifier,
        color = Color.Red,
        fontSize = 32.sp,
        fontStyle = FontStyle.Italic
    )
}

@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    LearnTextModifiersTheme {
        LearnTextAndModifiers()
    }
}