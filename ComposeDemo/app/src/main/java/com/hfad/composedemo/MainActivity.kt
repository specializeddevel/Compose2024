package com.hfad.composedemo

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfad.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun testAsignations(): Unit {
    var test = 3.14
    var test2: Int
    //NULLABLE TYPE
    lateinit var username: String

    username="Pedro"
    username.let {
        test2 = test.toInt()
        val res = if (test2 is Int) "int" else "nada"
        showToast(message = res)
        //showToast(test2.toString())
        val length = username.length
        println(username?.toUpperCase())
        //ELVIS OPERATOR if username is null then prints username is null
        (username ?: "username is null")
        //LET FUNCION print if username is not null
        username?.let {
            showToast("username is not null and has ${username.length} characters")
        }
    }
}

@Composable
fun showToast(message: String): Unit {
    val context = LocalContext.current
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}

@Composable
fun DemoText(message: String, fontSize: Float) {
    Text(
        text = message,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = fontSize.sp

    )
}

@Composable
fun DemoSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit) {
    Slider(
        modifier = Modifier.padding(10.dp),
        valueRange = 20f..38f,
        value = sliderPosition,
        onValueChange = { onPositionChange(it) }
    )
}

@Composable
fun DemoScreen(modifier: Modifier = Modifier) {
    modifier.padding(
        1000.dp
    )
    var sliderPosition by remember {
        mutableFloatStateOf(20f)
    }
    testAsignations()

    val handlePositionChange = { position: Float ->
        sliderPosition = position
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        DemoText(message = "This is compose 1.6! Yeap", fontSize = sliderPosition)
        Spacer(modifier = Modifier.height(150.dp))
        DemoSlider(sliderPosition = sliderPosition, onPositionChange = handlePositionChange)
        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = sliderPosition.toInt().toString() + "sp"
        )
        
    }

}

@Preview(showSystemUi = true)
@Composable
fun DemoTextPreview() {
    ComposeDemoTheme {
        DemoScreen()
    }
}