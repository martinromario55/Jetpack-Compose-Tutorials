package com.tuyiiya.floatingactionbutton

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.floatingactionbutton.ui.theme.ButtonGreen
import com.tuyiiya.floatingactionbutton.ui.theme.FloatingActionButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FloatingActionButtonTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    FloatingButtonView()
                    ExtendedFloatingButtonView()
                }
            }
        }
    }
}

@Composable
fun FloatingButtonView() {
    val context = LocalContext.current.applicationContext

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            containerColor = ButtonGreen,
            onClick = {
                Toast.makeText(context, "Floating Action Button clicked", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
        }
    }
}

@Composable
fun ExtendedFloatingButtonView() {
    val context = LocalContext.current.applicationContext

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            containerColor = ButtonGreen,
            onClick = {
                Toast.makeText(context, "Extended Floating Action Button clicked", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "add")
            Text(
                text = "Menu",
                modifier = Modifier.padding(4.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FloatingActionButtonTheme {
//        FloatingButtonView()
        ExtendedFloatingButtonView()
    }
}