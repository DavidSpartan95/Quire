package com.example.quire.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.components.NoteComp

@Composable
fun TaskItemScreen(navController: NavController, userRepository: UserRepository) {

    val testNote = Note("TestTitle","Content Text","Idag",false,"TagTest")
    NoteComp(navController = navController, note = testNote, userRepository)
}