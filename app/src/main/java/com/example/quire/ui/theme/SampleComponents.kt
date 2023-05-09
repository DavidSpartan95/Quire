package com.example.quire.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quire.R
//TODO Delete
@Composable
fun Prototype(navController: NavController) {
    Column(Modifier.fillMaxWidth(),Arrangement.Top, Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(height = 75.dp))
        Box(
            Modifier
                .size(250.dp)
                , contentAlignment = Alignment.TopCenter) {
            //TODO Change the ic_launcher_foreground to new pic
            Image(painter = painterResource(id = R.drawable.body),
                contentDescription = "Homescreen logo")
        }
        Spacer(modifier = Modifier.height(height = 20.dp))

        Text(text = "Quire", fontWeight = FontWeight.Bold )

        Spacer(modifier = Modifier.height(height = 40.dp))
        
        Text(text = "Make notes with Quire\r\n app", textAlign = TextAlign.Center,)

        Spacer(modifier = Modifier.height(height = 100.dp))
        
        Button(onClick = {
            navController.navigate(route = "task_screen"){

            }
        }, Modifier.width(250.dp),  colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(100,210,193),
            contentColor = Color.Black)
        ) {
            Text(text = "Get Started")
        }

    }
    
}


