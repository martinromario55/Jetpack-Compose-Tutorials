package com.tuyiiya.navigationdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tuyiiya.navigationdrawer.ui.theme.NavigationDrawerTheme
import kotlinx.coroutines.launch
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerContent { scope.launch { drawerState.close() } }
                    },
                ) {
                    Scaffold(
                        topBar = {
                            AppBar(
                                onNavigationIconClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                    ) {  padding ->
                        Text(
                            text = "Main Content Here",
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun DrawerContent(onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxHeight()
            .padding(horizontal = 16.dp)
    ) {
        DrawerHeader()
        DrawerBody(
            items = listOf(
                MenuItem(
                    id = "home",
                    title = "Home",
                    contentDescription = "Go to home Screen",
                    icon = Icons.Default.Home
                ),
                MenuItem(
                    id = "settings",
                    title = "Settings",
                    contentDescription = "Go to settings Screen",
                    icon = Icons.Default.Settings
                ),
                MenuItem(
                    id = "help",
                    title = "Help",
                    contentDescription = "Go to help Screen",
                    icon = Icons.Default.Info
                )
            ),
            onItemClick = { onItemClick }
        )
    }
}