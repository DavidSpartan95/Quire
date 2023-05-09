package com.example.quire.ui.theme.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quire.R



val blueColor = Color(0xFF4ECCD3)
val backgroundColor = Color(0xFFEEEEEE)

@Composable
fun HomeScreenContent(navController: NavController) {
    Surface(color = backgroundColor) {
        val context = LocalContext.current
        //val hasSeenIntro = AppPreferences.hasSeenIntro(context)

        //if (!hasSeenIntro) {
        //if (hasSeenIntro) {

          //  IntroductionDialog(onDismiss = { AppPreferences.setHasSeenIntro(context, true) })
        //}

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageTop = painterResource(R.drawable.moln)
            val imageMiddle = painterResource(R.drawable.icon)

            Image(
                painter = imageTop,
                contentDescription = "Moln",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(178.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = imageMiddle,
                contentDescription = "Icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Quire",
                color = blueColor,
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Make notes with Quire app",
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(55.dp))

            Button(
                onClick = { navController.navigate("task_screen") },
                colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),
                modifier = Modifier.width(320.dp)
            ) {
                Text(
                    text = "Get started",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

