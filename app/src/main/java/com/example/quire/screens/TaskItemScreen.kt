package com.example.quire.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.ui.theme.components.notes.NoteComp

@Composable
fun TaskItemScreen(navController: NavController, userRepository: UserRepository) {

    NoteComp(navController = navController, userRepository)
}