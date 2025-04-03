package com.tuyiiya.jcbottomsheeet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.jcbottomsheeet.ui.theme.JCBottomSheeetTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            JCBottomSheeetTheme {
                val sheetState = rememberModalBottomSheetState()
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState
                )
                val scope = rememberCoroutineScope()

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bottom Sheet",
                                fontSize = 60.sp
                            )
                        }
                    },
                    sheetContainerColor = Color.Green
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                scope.launch {
                                    if (sheetState.isVisible) {
                                        sheetState.hide()
                                    } else {
                                        sheetState.show()
                                    }
                                }
                            }
                        ) {
                            Text("Toggle Sheet")
                        }
                    }
                }
            }
        }
    }
}
