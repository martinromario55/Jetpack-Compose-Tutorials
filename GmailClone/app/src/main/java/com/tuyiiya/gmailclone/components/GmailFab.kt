package com.tuyiiya.gmailclone.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GmailFab(scrollState: ScrollState) {
    if (scrollState.value > 100) {
        ExtendedFloatingActionButton(
            onClick = {},
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Icon(imageVector = Icons.Default.Edit, "", modifier = Modifier.padding(end = 10.dp))
            Text(text = "Compose", fontSize = 16.sp)
        }
    } else {
        FloatingActionButton(
            onClick = {},
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Icon(imageVector = Icons.Default.Edit, "")
        }
    }
}