package com.example.quire.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.components.NoteScreen
import com.example.quire.utilities.addNewNote
import kotlinx.coroutines.Dispatchers

@Composable
fun TaskScreen(navController: NavController, userRepository: UserRepository) {

    var notes: Array<Note>? by remember { mutableStateOf(null) }
    var update: Boolean  by remember { mutableStateOf(false) }

    LaunchedEffect(true,update){
        if (update){update = false}
        userRepository.performDatabaseOperation(Dispatchers.IO){
            try {
                 notes = userRepository.getUserInfo()[0].notes
            }
            catch (e: Exception){
                println(e)
            }
        }
    }

    notes?.let {
        NoteScreen(
            navController,
            userRepository,notes = notes!!,
            onAddClick = {addNewNote(userRepository) { update = true }}
        ) { update = true }

    }
}