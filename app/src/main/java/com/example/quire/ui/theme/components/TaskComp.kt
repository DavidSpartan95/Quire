package com.example.quire.ui.theme.components


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.utilities.deleteNote


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    userRepository: UserRepository,
    notes: Array<Note>,
    onAddClick:() -> Unit,
    update:() -> Unit
) {
    val searchValue by remember {
        mutableStateOf("")
    }
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
              title = {
                    TextField(
                        value =searchValue ,
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .background(color = Color.White,
                            shape = RoundedCornerShape(30.dp)
                            ),
                        placeholder = {
                            Text(text = "Search...",
                                fontSize = 11.sp)},
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search")
                            }
                        }
                        )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                content = {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add note"
                    )
                }
            )
        }
    ) {
        LazyColumn {
            itemsIndexed(notes) { index,note ->
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    NoteItem(note = note) {
                            deleteNote(userRepository, index,mainTread = { update.invoke() })
                    }
                }
            }
        }
    }
}