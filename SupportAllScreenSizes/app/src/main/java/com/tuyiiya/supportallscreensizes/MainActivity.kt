package com.tuyiiya.supportallscreensizes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.supportallscreensizes.ui.theme.SupportAllScreenSizesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SupportAllScreenSizesTheme {
                val windowInfo = rememberWindowInfo()

                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(10) {
                            Text(
                                text = "Item $it",
                                fontSize = 25.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Cyan)
                                    .padding(16.dp)
                            )
                        }

                        items(10) {
                            Text(
                                text = "Item $it",
                                fontSize = 25.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                                    .padding(16.dp)
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(10) {
                                Text(
                                    text = "Item $it",
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Cyan)
                                        .padding(16.dp)
                                )
                            }
                        }

                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {

                            items(10) {
                                Text(
                                    text = "Item $it",
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Green)
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
