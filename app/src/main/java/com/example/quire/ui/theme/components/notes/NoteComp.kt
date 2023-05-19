package com.example.quire.ui.theme.components.notes

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quire.R
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.folder.Folder
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.backgroundColor
import com.example.quire.ui.theme.blueColor
import com.example.quire.utilities.addNewNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteComp(navController: NavController, userRepository: UserRepository, specificNote: Note? = null, noteIndex: Int?= null, folders: Array<Folder>? = null) {

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

			if(noteIndex != null && specificNote != null){
				EditNote(userRepository, noteIndex  = noteIndex, specificNote = specificNote, folders = folders){
					navController.popBackStack()
				}
			}else {
				EditNote(userRepository) {
					navController.popBackStack()
				}

			}
		}
	}
}

@Composable
fun EditNote(userRepository: UserRepository,specificNote: Note? = null, noteIndex: Int?= null,folders:Array<Folder>? = null ,popBack: ()-> Unit,) {

	var folders by remember{ mutableStateOf(folders) }
	var expand by remember {
		mutableStateOf(false)
	}
	var title by remember {
		//check if the note that is being clicked is not empty and fetch the specific note value to the title
		if (specificNote != null){

			mutableStateOf(specificNote.title)
		}else{
			mutableStateOf("")
		}

	}
	var content by remember {
		if(specificNote != null){
			mutableStateOf(specificNote.content)
		}else{
			mutableStateOf("")
		}

	}

	val context = LocalContext.current  // Access the context

	fun showToast(message: String) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
	}

	Box(modifier = Modifier) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.align(Alignment.Center)
				.padding(15.dp)

		) {

			Spacer(modifier = Modifier.height(15.dp))

			OutlinedTextField(
				value = title.take(20),
				onValueChange = { newValue ->
					if (newValue.length <= 20) {
						title = newValue
					}
				},
				label = { Text("Titel", color = blueColor) },
				colors = TextFieldDefaults
					.outlinedTextFieldColors(
						focusedBorderColor = blueColor,
						unfocusedBorderColor = blueColor,
						backgroundColor = Color.White
					),

				modifier = Modifier
					.fillMaxWidth()
			)

			Spacer(modifier = Modifier.height(10.dp))

			OutlinedTextField(
				value = content,
				onValueChange = { content = it },
				label = { Text("Your note:", color = blueColor) },
				colors = TextFieldDefaults
					.outlinedTextFieldColors(
						focusedBorderColor = blueColor,
						unfocusedBorderColor = blueColor,
						backgroundColor = Color.White
					),
				modifier = Modifier
					.fillMaxWidth()
					.height(200.dp)
			)

			Spacer(modifier = Modifier.height(15.dp))

			// Save the note
			Button(modifier = Modifier
				.align(Alignment.End),
				colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),
				onClick = {
					if (title.isNotEmpty() && content.isNotEmpty()) {
						if (specificNote != null && noteIndex != null) {
							userRepository.performDatabaseOperation(Dispatchers.IO) {
								userRepository.editExitingNote(noteIndex, content, title)
								CoroutineScope(Dispatchers.Main).launch {
									showToast("Note changed")
									popBack.invoke()
								}
							}
						} else {
							addNewNote(title, content, userRepository) {
								showToast("Note saved")
								popBack.invoke()

							}
						}

					} else {
						showToast("The fields can not be empty!")
					}
				}
			) {
				Text(text = "Save")
			}
			Box {
				IconButton(onClick = { expand = true }) {
					Icon(
						painter = painterResource(id = R.drawable.icon_folder),
						contentDescription = "Folder",
						tint = blueColor,
						modifier = Modifier.size(26.dp)
					)
				}
				DropdownMenu(
					expanded = expand,
					onDismissRequest = { expand = false }
				) {
					folders?.let {
						for (x in folders!!) {
							Text(x.name, fontSize=18.sp, modifier = Modifier
								.padding(10.dp)
								.clickable(onClick = {}))

						}
					}
					}
				}
			}

		}
	}
