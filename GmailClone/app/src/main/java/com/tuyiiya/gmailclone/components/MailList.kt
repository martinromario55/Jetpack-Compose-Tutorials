package com.tuyiiya.gmailclone.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.gmailclone.GmailApp
import com.tuyiiya.gmailclone.mailList
import com.tuyiiya.gmailclone.model.MailData
import com.tuyiiya.gmailclone.ui.theme.GmailCloneTheme

@Composable
fun MailList(paddingValues: PaddingValues, scrollState: ScrollState) {
    Box(
        modifier = Modifier.padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp)
                .scrollable(scrollState, Orientation.Vertical)
        ) {
            items(mailList) { mailData ->
                MailItem(mailData = mailData)
            }
        }
    }
}

@Composable
fun MailItem(
    mailData: MailData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(bottom = 8.dp)
    ) {
        Card(
            modifier = modifier.padding(end = 8.dp)
                .size(40.dp)
                .clip(CircleShape),
            colors = CardDefaults.cardColors()
        ) {
            Text(
                text = mailData.userName[0].toString(),
                textAlign = TextAlign.Center,
                modifier = modifier.padding(
                    top = 8.dp,
                    start = 14.dp
                )
            )
        }

        Column(
            modifier.weight(2.0f)
        ) {
            Text(
                text = mailData.userName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = mailData.subject,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = mailData.body,
                fontSize = 14.sp
            )
        }

        Column {
            Text(text = mailData.timeStamp)

            IconButton(
                onClick = {},
                modifier = Modifier.size(50.dp).padding(top = 16.dp)
            ) {
                Icon(imageVector = Icons.Outlined.StarOutline, "")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MailItemPreview() {
    MailItem (
        mailData = MailData(
            mailId = 4,
            userName = "Angelo",
            subject = "Email regarding something important",
            body = "This is regarding an important opportunity",
            timeStamp = "22:22"
        )
    )
}