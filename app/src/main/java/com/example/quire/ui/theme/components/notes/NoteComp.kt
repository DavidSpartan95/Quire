package com.example.quire.ui.theme.components.notes

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.ui.theme.backgroundColor
import com.example.quire.utilities.addNewNote
import com.example.quire.utilities.setItalicFont


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


			EditNote(userRepository = userRepository, ){
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

	var isItalic by remember {
		mutableStateOf(false)
	}

	var isBold by remember {
		mutableStateOf(false)
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
				value = titel.take(20),
				onValueChange = {newValue ->
					if (newValue.length <= 20) {
						titel = newValue}
								},
				label = {Text("Titel", color = Color(0xFF4ECCD3)) },
				textStyle = TextStyle(
					fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
					fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
				),
				colors = TextFieldDefaults
					.outlinedTextFieldColors(
						focusedBorderColor = Color(0xFF4ECCD3),
						unfocusedBorderColor = Color(0xFF4ECCD3)),

				modifier = Modifier
					.fillMaxWidth())

			Spacer(modifier = Modifier.height(10.dp))

			OutlinedTextField(value = content,
				onValueChange = {content = it},
				label = {Text("Your note:", color = Color(0xFF4ECCD3)) },
				textStyle = TextStyle(
					fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal,
					fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
				),
				colors = TextFieldDefaults
					.outlinedTextFieldColors(
						focusedBorderColor = Color(0xFF4ECCD3),
						unfocusedBorderColor = Color(0xFF4ECCD3)),
				modifier = Modifier
					.fillMaxWidth()
					.height(200.dp))

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Start,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = "I",
					modifier = Modifier
						.padding(end = 8.dp)
						.clickable {
							isItalic = !isItalic }
						.background(if (isItalic) Color.Gray else Color.Transparent)
						.padding(8.dp),
					style = TextStyle(
						fontStyle = FontStyle.Italic,
						fontWeight = FontWeight.ExtraBold
					)
				)

				Text(
					text = "B",
					modifier = Modifier
						.padding(end = 8.dp)
						.clickable { isBold = !isBold }
						.background(if (isBold) Color.Gray else Color.Transparent)
						.padding(8.dp),
					style = TextStyle(
						fontWeight = FontWeight.ExtraBold
					)
				)


			}



			Button(modifier = Modifier
				.align(Alignment.End),
				colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECCD3)),
				onClick = {
					if (titel.isNotEmpty() && content.isNotEmpty()) {

						addNewNote(titel,content,userRepository){
							showToast("Note saved")
							popBack.invoke()

						}

						// Save the note
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