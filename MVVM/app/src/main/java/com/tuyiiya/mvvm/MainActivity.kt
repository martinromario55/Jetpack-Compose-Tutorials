package com.tuyiiya.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tuyiiya.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMTheme {
                Surface (modifier = Modifier.fillMaxSize().padding(top = 50.dp)) {
                    Counter()
                }
            }
        }
    }
}

@Composable
fun Counter(myViewModel: MyViewModel = viewModel()) {
//    var count by remember {
//        mutableStateOf(0)
//    }

    Column {
        Button(onClick = {
//            count++
            myViewModel.incrementCounter()
        }) {
            Text(text = "Click Me")
        }
//        Text(text = "Counter Value: $count")
        Text(text = "Counter Value: ${myViewModel.count}")
    }
}

