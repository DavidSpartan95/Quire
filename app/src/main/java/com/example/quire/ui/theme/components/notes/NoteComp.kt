package com.example.quire.ui.theme.components.notes

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.ui.theme.backgroundColor
import com.example.quire.ui.theme.blueColor
import com.example.quire.utilities.addNewNote


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteComp(navController: NavController, userRepository: UserRepository) {

	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text("Your Note") },
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(Icons.Default.ArrowBack, contentDescription = "Back")
					}
				},
				backgroundColor = Color(0,205,212,),
				contentColor = Color.White
			)
		},

	) {
		Surface(color = backgroundColor) {


			EditNote(userRepository){
				navController.popBackStack()
			}


		}
	}
}

@Composable
fun EditNote(userRepository: UserRepository, popBack: ()-> Unit) {
	var titel by remember {
		mutableStateOf("")
	}
	var content by remember {
		mutableStateOf("")
	}

	val context = LocalContext.current  // Access the context

	fun showToast(message: String) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
	}

	Box(modifier = Modifier){
		Column(modifier = Modifier
			.fillMaxSize()
			.align(Alignment.Center)
			.padding(15.dp)

		) {

			Spacer(modifier = Modifier.height(15.dp))

			OutlinedTextField(
				value = titel,
				onValueChange = {titel = it},
				label = {Text("Titel", color = blueColor) },
				colors = TextFieldDefaults
					.outlinedTextFieldColors(
						focusedBorderColor = blueColor,
						unfocusedBorderColor = blueColor,
						backgroundColor = Color.White
					),

				modifier = Modifier
					.fillMaxWidth())

			Spacer(modifier = Modifier.height(10.dp))

			OutlinedTextField(value = content,
				onValueChange = {content = it},
				label = {Text("Your note:", color = blueColor) },
				colors = TextFieldDefaults
					.outlinedTextFieldColors(
						focusedBorderColor = blueColor,
						unfocusedBorderColor = blueColor,
						backgroundColor = Color.White
					),
				modifier = Modifier
					.fillMaxWidth()
					.height(200.dp))

			Spacer(modifier = Modifier.height(15.dp))

			// Save the note
			Button(modifier = Modifier
				.align(Alignment.End),
				colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),
				onClick = {
					if (titel.isNotEmpty() && content.isNotEmpty()) {

						addNewNote(titel,content,userRepository){
							showToast("Note saved")
							popBack.invoke()

						}
					} else {
						showToast("The fields can not be empty!")
					}
				}
			) {
				Text( text = "Save")
			}
		}

	}

}