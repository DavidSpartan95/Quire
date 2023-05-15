package com.example.quire.ui.theme.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quire.R
import com.example.quire.dataBase.User
import com.example.quire.dataBase.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val blueColor = Color(0xFF4ECCD3)
val backgroundColor = Color(0xFFEEEEEE)

@Composable
fun HomeScreenContent(navController: NavController, userRepository: UserRepository) {
    var userExists: Boolean? by remember { mutableStateOf(null)}
    var showAlert: Boolean  by remember { mutableStateOf(true)}

    LaunchedEffect(true){
        userRepository.performDatabaseOperation(Dispatchers.IO){
            userExists = userRepository.getUserInfo().isNotEmpty()
            println(userExists)
        }
    }

    Surface(color = backgroundColor) {

        if(userExists != null){
            if (!userExists!!){
                if (showAlert){
                    IntroductionDialog(){ showAlert = false }
                }
            }
        }

        Box(modifier = Modifier){
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = "background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomCenter)
            ) {
                /*
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
				*/
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp),
                    text = "Quire",
                    color = blueColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp

                )

                Text(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 80.dp),
                    text = "Make notes with Quire app",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )


                Button(
                    onClick = {
                        //This code will make a new user if there is no user in the database
                        // Then it will navigate to homeScreen
                        userRepository.performDatabaseOperation(Dispatchers.IO){
                            if (userRepository.getUserInfo().isEmpty()) {
                                userRepository.addUser(User(notes = arrayOf()))
                                CoroutineScope(Dispatchers.Main).launch {
                                    navController.navigate("task_screen")
                                }
                            }else{
                                CoroutineScope(Dispatchers.Main).launch {
                                    navController.navigate("task_screen")
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),
                    modifier = Modifier
                        .width(320.dp)
                        .padding(bottom = 80.dp)
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
}
