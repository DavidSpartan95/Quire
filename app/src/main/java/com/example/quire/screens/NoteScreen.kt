package com.example.quire.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.components.NoteComp

@Composable
fun NoteScreen2(navController: NavController, userRepository: UserRepository) {

	Surface(
		Modifier.fillMaxSize(),
		color = Color(238,238,238)
	) {

		//Prototype(navController)
		NoteComp (navController = navController, note = Note(title = String(), content = String(), tag = String()))

	}

}