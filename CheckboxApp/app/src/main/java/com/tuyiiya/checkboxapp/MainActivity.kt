package com.tuyiiya.checkboxapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuyiiya.checkboxapp.ui.theme.CheckboxAppTheme
import com.tuyiiya.checkboxapp.ui.theme.CheckboxGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            CheckboxAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    ) {
                    CheckboxesView()
                }
            }
        }
    }
}

@Composable
fun CheckboxesView() {
    val todoList: List<String> = listOf("Buy Groceries", "Go Shopping", "Create a Project", "Go for a Walk", "Play Games", "Read a Book")
    val context = LocalContext.current.applicationContext

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painterResource(id = R.drawable.bgimage),
                contentScale = ContentScale.FillWidth
            )
    ) {
        Row (
            modifier = Modifier.padding(top = 25.dp).fillMaxWidth().height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TODO List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = CheckboxGreen
            )
        }

        Column(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    top = 40.dp
                )
        ) {
            todoList.forEach{
                todoItem ->
                var checkedItem by remember {
                    mutableStateOf(false)
                }

                Row {
                    Checkbox(
                        checked = checkedItem,
                        onCheckedChange = {
                                checkStatus -> checkedItem = checkStatus
                            Toast.makeText(context, "$todoItem is $checkedItem", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Text(
                        text = todoItem,
                        fontSize = 22.sp,
                        color = CheckboxGreen
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CheckboxAppTheme {
        CheckboxesView()
    }
}