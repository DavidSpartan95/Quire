package com.example.quire.ui.theme.components


import NavBarComp
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.backgroundColor
import com.example.quire.ui.theme.blueColor
import com.example.quire.ui.theme.components.notes.NoteItem
import com.example.quire.utilities.deleteNote
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    userRepository: UserRepository,
    notes: Array<Note>,
    onAddClick:() -> Unit,
    update:() -> Unit,

) {
    var searchValue by remember {
        mutableStateOf("")
    }
    var contentShown by remember {
        mutableStateOf("TaskScreen")
    }


    val filteredNotes = notes.filter { note ->
        note.title.contains(searchValue, ignoreCase = true) ||
                note.content.contains(searchValue, ignoreCase = true)
    }.toTypedArray()


    Scaffold(
        backgroundColor = backgroundColor,
        topBar = {
            TopAppBar(
                backgroundColor = backgroundColor,
                title = {
                    TextField(
                        value = searchValue,
                        onValueChange = { newValue -> searchValue = newValue },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(30.dp)
                            ),
                        placeholder = {
                            Text(
                                text = "Search...",
                                fontSize = 11.sp
                            )
                        },
                        textStyle = TextStyle(fontSize = 13.sp),
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                backgroundColor = blueColor,
                content = {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add note"
                    )
                }
            )

        }, bottomBar = {
            NavBarComp(
                task = {
                    contentShown = "TaskScreen"
                    update.invoke()
                       },
                favorite = {
                    contentShown = "FavoriteScreen"
                    update.invoke()
                           },
                folder = {}

            )
        }
    ) {
        Column(modifier = Modifier.padding(bottom = 130.dp)){ // Wrap the LazyColumn with a Column composable
            Text(
                text = "Notes",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
            )
            when(contentShown){
                "TaskScreen" -> {LazyColumn {
                    itemsIndexed(filteredNotes) { index, note ->
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth().clickable { println("ClickClick") }


                        ) {
                            NoteItem(note = note, userRepository = userRepository, i = index, update = update
                                , onDeleteClick = {deleteNote(userRepository, index,mainTread = { update.invoke() })} )

                        } }


                }}
                "FavoriteScreen" -> {LazyColumn {
                    itemsIndexed(filteredNotes) { index, note ->
                        if (note.favorite){
                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth().clickable { println("ClickClick") }


                            ) {
                                NoteItem(note = note, userRepository = userRepository, i = index, update = update
                                    , onDeleteClick = {deleteNote(userRepository, index,mainTread = { update.invoke() })} )

                            }
                        }
                    }


                }}
                else -> {}
            }


        }
    }

}
