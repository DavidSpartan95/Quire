package com.example.quire.screens

import NavBarComp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.components.NoteScreen
import com.example.quire.utilities.addNewNote
import com.example.quire.utilities.deleteNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            update = { update = true }
        )

            //deleteNote(userRepository,0){ update = true }

    }

}