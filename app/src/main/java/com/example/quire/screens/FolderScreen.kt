package com.example.quire.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.quire.dataBase.UserRepository

@Composable
fun FolderScreen(userRepository: UserRepository) {

    Text(text = "Hello")
    Button(onClick = {
        //TODO Add Folder
    }) {
        Text(text = "Add Folder")
    }

}