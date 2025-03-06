package com.tuyiiya.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.tuyiiya.constraintlayout.ui.theme.ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintLayoutTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    ConstraintLayoutView()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutView () {
    ConstraintLayout {
        val (redButton, greenButton, blueButton, yellowButton) = createRefs()

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier.constrainAs(redButton){
                top.linkTo(parent.top)
                width = Dimension.matchParent
                height = Dimension.value(100.dp)
            }
        ) {
            Text(text = "Red")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Green),
            modifier = Modifier.constrainAs(greenButton){
                top.linkTo(redButton.bottom)
            }
        ) {
            Text(text = "Green")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier.constrainAs(blueButton){
                top.linkTo(redButton.bottom)
            }
        ) {
            Text(text = "Blue")
        }
        createHorizontalChain(greenButton, blueButton, chainStyle = ChainStyle.Spread)

        val guidLine = createGuidelineFromBottom(0.01f)
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Yellow),
            modifier = Modifier.constrainAs(yellowButton){
//                top.linkTo(blueButton.bottom)
                bottom.linkTo(guidLine)
            }
        ) {
            Text(text = "Yellow")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintLayoutTheme {
        ConstraintLayoutView()
    }
}