package com.tuyiiya.gmailclone.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tuyiiya.gmailclone.model.BottomMenuData

@Composable
fun HomeBottomMenu () {
    val menuItems = listOf(
        BottomMenuData.Mail,
        BottomMenuData.Meet
    )


    NavigationBar (
        containerColor = Color.White,
        contentColor = Color.Black
    ){
        menuItems.forEach {
            NavigationBarItem (
                label = {Text(text = it.title)},
                selected = false,
                alwaysShowLabel = true,
                onClick = {},
                icon = { Icon(imageVector = it.icon, contentDescription = it.title)}
            )
        }
    }
}