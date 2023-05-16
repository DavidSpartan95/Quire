package com.example.quire.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.components.NoteScreen
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
            userRepository,notes = notes!!,
            onAddClick = {navController.navigate("task_item_screen")},
            update = { update = true },
        )

    }

}