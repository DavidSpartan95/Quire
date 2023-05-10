package com.example.quire.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun EditNote() {
    val titel by remember {
        mutableStateOf("")
    }
    val content by remember {
        mutableStateOf("")
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
                onValueChange = {},
                label = {Text("Titel", color = Color(0xFF4ECCD3)) },
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF4ECCD3),
                        unfocusedBorderColor = Color(0xFF4ECCD3)),

                modifier = Modifier
                    .fillMaxWidth())

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = content,
                onValueChange = {},
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
                onClick = { /*TODO*/ }) {
                Text( text = "Save")

            }
        }

    }
    
}
