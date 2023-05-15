package com.example.quire.ui.theme.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quire.dataBase.UserRepository
import com.example.quire.utilities.addNewNote


@Composable
fun EditNote(userRepository: UserRepository) {
    var titel by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current  // Access the context

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    Box(modifier = Modifier){
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)
            .padding(15.dp)

            ) {

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = titel,
                onValueChange = {titel = it},
                label = {Text("Titel", color = Color(0xFF4ECCD3)) },
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF4ECCD3),
                        unfocusedBorderColor = Color(0xFF4ECCD3)),

                modifier = Modifier
                    .fillMaxWidth())

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = content,
                onValueChange = {content = it},
                label = {Text("Your note:", color = Color(0xFF4ECCD3)) },
                colors =TextFieldDefaults
                    .outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF4ECCD3),
                        unfocusedBorderColor = Color(0xFF4ECCD3)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp))


            Button(modifier = Modifier
                .align(Alignment.End),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECCD3)),
                onClick = {
                    if (titel.isNotEmpty() && content.isNotEmpty()) {
                        addNewNote(titel,content,userRepository){
                            showToast("Note saved")
                        }

                    // Save the note
                } else {
                    showToast("The fields can not be empty!")
                }
                }
            ) {
                Text( text = "Save")
            }
        }

    }
    
}
