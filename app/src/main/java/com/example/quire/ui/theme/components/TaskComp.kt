package com.example.quire.ui.theme.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.utilities.deleteNote


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    navController: NavController,
    userRepository: UserRepository,
    notes: Array<Note>,
    onAddClick:() -> Unit,
    update:() -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note List") }
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
            itemsIndexed(notes) { index, note ->
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
                {
                    NoteItem(
                        note = note,
                        onDeleteClick = {
                            deleteNote(
                                userRepository,
                                index,
                                mainTread = { update.invoke() })
                        },
                        onItemClick = {
                            navController.navigate("note_screen")
                        }



                    ) {

                    }
                }
            }
        }
    }
}