package com.example.quire.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.folder.Folder
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.components.NoteScreen
import kotlinx.coroutines.Dispatchers

@Composable
fun TaskScreen(navController: NavController, userRepository: UserRepository) {

    var notes: Array<Note>? by remember { mutableStateOf(null) }
    var update: Boolean  by remember { mutableStateOf(false) }
    var folders: Array<Folder>? by remember { mutableStateOf(null) }

    LaunchedEffect(true,update){
        if (update){update = false}
        userRepository.performDatabaseOperation(Dispatchers.IO){
            try {
                 notes = userRepository.getUserInfo()[0].notes
                folders = arrayOf(Folder("Test", notes!!),Folder("Test2", notes!!),Folder("Test3", notes!!))
            }
            catch (e: Exception){
                println(e)
            }
        }
    }

    notes?.let {
        NoteScreen(
            navController = navController,
            userRepository,
            notes = notes!!,
            onAddClick = {navController.navigate("task_item_screen")},
            update = { update = true },
            folders = folders!!,
        )

    }

}