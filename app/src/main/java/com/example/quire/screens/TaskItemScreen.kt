package com.example.quire.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.room.Index
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.components.notes.NoteComp

@Composable
fun TaskItemScreen(navController: NavController, userRepository: UserRepository, specificNote: Note? = null, noteIndex: Int?= null) {

    if (specificNote != null && noteIndex != null){
        NoteComp(navController = navController, userRepository,specificNote = specificNote, noteIndex = noteIndex)
    }else{
        NoteComp(navController = navController, userRepository)

    }

}