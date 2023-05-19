package com.example.quire.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.room.Index
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.folder.Folder
import com.example.quire.dataBase.note.Note
import com.example.quire.screens.navGraph.Screen
import com.example.quire.ui.theme.components.notes.NoteComp
import kotlinx.coroutines.Dispatchers

@Composable
fun TaskItemScreen(navController: NavController, userRepository: UserRepository, specificNote: Note? = null, noteIndex: Int?= null, folder: Array<Folder>? = null) {

    if (specificNote != null && noteIndex != null){
        NoteComp(navController = navController, userRepository,specificNote = specificNote, noteIndex = noteIndex, folders = folder)
    }else{
        NoteComp(navController = navController, userRepository)

    }

}