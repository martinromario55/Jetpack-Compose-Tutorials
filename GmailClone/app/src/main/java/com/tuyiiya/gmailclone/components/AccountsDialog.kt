package com.tuyiiya.gmailclone.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tuyiiya.gmailclone.R
import com.tuyiiya.gmailclone.accountList
import com.tuyiiya.gmailclone.model.Account

@Composable
fun AccountsDialog(openDialog: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = {openDialog.value = false},
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        AccountDialogUI(openDialog = openDialog)
    }
}

@Composable
fun AccountDialogUI(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean>
){
    Card() {
        Column(
            modifier.background(Color.White)
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { openDialog.value = false}
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
                }
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "",
                    modifier.size(30.dp).weight(2.0f)
                )
            }
            AccountItem(account = accountList[0])
//
//            Row(
//                modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.profilepic),
//                    contentDescription = "Profile",
//                    modifier = Modifier.size(30.dp)
//                        .clip(CircleShape)
//                        .background(color = Color.Gray)
//                )
//                Column(
//                    modifier.weight(2.0f)
//                        .padding(start = 16.dp, bottom = 16.dp)
//                ) {
//                    Text(
//                        text = "Tuyiiya Web",
//                        fontWeight = FontWeight.SemiBold
//                    )
//                    Text(text = "tuyiiyaweb@yopmail.com")
//                }
//                Text(
//                    text = "99+",
//                    modifier = Modifier.padding(end = 16.dp)
//                )
//            }
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier.requiredWidth(150.dp),
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(1.dp, color = Color.Gray)
                ) {
                    Text(
                        text = "Google Account",
                        modifier.padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier.width(10.dp))
            }

            HorizontalDivider(modifier.padding(top = 16.dp))

            Column {
                AccountItem(accountList[1])
                AccountItem(accountList[2])
            }

            AddAccount(
                icon = Icons.Default.PersonAddAlt,
                "Add Another Account"
            )
            AddAccount(
                icon = Icons.Outlined.ManageAccounts,
                "Manage Accounts on this device"
            )

            HorizontalDivider(modifier.padding(top = 16.dp, bottom = 16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Privacy Policy"
                )

                Card(
                    modifier = Modifier.padding(top = 10.dp)
                        .size(3.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(Color.Black)
                ) {  }

                Text(
                    text = "Terms of Service"
                )
            }
        }
    }
}

@Composable
fun AccountItem(account: Account) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp)
    ) {
        if (account.icon != null) {
            Image(
                painter = painterResource(id = account.icon),
                contentDescription = "Profile",
                modifier = Modifier.size(30.dp)
                    .clip(CircleShape)
                    .background(color = Color.Gray)
            )
        } else {
            Card(
                modifier = Modifier.padding(end = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape),
                colors = CardDefaults.cardColors()
            ) {
                Text(
                    text = account.userName[0].toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 14.dp
                    )
                )
            }
        }

        Column(
            modifier = Modifier.weight(2.0f)
                .padding(start = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = account.userName,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = account.email)
        }
        Text(
            text = "${account.unReadMails}+",
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Composable
fun AddAccount(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp)
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun AccountsDialogUIPreview() {
    AccountDialogUI( openDialog = mutableStateOf(false))
}