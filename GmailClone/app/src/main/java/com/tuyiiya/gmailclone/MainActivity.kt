package com.tuyiiya.gmailclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tuyiiya.gmailclone.components.GmailDrawerMenu
import com.tuyiiya.gmailclone.components.HomeAppBar
import com.tuyiiya.gmailclone.components.HomeBottomMenu
import com.tuyiiya.gmailclone.ui.theme.GmailCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            GmailCloneTheme {
                Surface (color = MaterialTheme.colorScheme.background) {
                    GmailApp()
                }
            }
        }
    }
}

@Composable
fun GmailApp() {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,

    )
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                GmailDrawerMenu(scrollState)
            }
        }
    ) {
        Scaffold(
            topBar = { HomeAppBar(
                drawerState,
                coroutineScope
            ) },
            bottomBar = {
                HomeBottomMenu()
            }
        ) {  }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GmailCloneTheme {
        GmailApp()
    }
}