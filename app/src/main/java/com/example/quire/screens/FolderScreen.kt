package com.example.quire.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.folder.Folder
import com.example.quire.ui.theme.backgroundColor
import com.example.quire.ui.theme.blueColor
import com.example.quire.utilities.addFolder


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FolderScreen(userRepository: UserRepository, navController: NavController) {

    var folderName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Folder") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = Color(0, 205, 212,),
                contentColor = Color.White
            )
        }

    ) {
        Surface(color = backgroundColor) {Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            OutlinedTextField(
                value = folderName.take(20),
                onValueChange = {newValue ->
                    if (newValue.length <= 20) {
                        folderName = newValue}
                },
                label = {Text("Folder Name", color = blueColor) },
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(
                        focusedBorderColor = blueColor,
                        unfocusedBorderColor = blueColor,
                        backgroundColor = Color.White
                    ),

                modifier = Modifier
                    .padding(bottom=15.dp)
                    .fillMaxWidth())

            Button(modifier = Modifier
                .align(Alignment.End),
                colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),
                onClick = {
                    addFolder(userRepository,Folder(folderName, arrayOf()), mainTread = {navController.popBackStack()})
                }
            ) {
                Text( text = "Save")
            }
        }
        }
    }
}
