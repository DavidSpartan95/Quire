package com.example.quire.ui.theme.components


import NavBarComp
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quire.dataBase.User
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.folder.Folder
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.backgroundColor
import com.example.quire.ui.theme.blueColor
import com.example.quire.ui.theme.components.notes.NoteItem
import com.example.quire.utilities.deleteNote
import com.example.quire.utilities.removeFolder
import com.google.gson.Gson
import java.util.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    user: User,
    navController: NavController,
    userRepository: UserRepository,
    notes: Array<Note>,
    folders: Array<Folder>,
    onAddClick:() -> Unit,
    update:() -> Unit,

    ) {
    var showDialog by remember { mutableStateOf(false) }
    var folderIndex:Int? by remember { mutableStateOf(null) }
    var searchValue by remember {
        mutableStateOf("")
    }
    var contentShown by remember {
        mutableStateOf("TaskScreen")
    }

    val shape = RoundedCornerShape(15.dp)


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
                onClick = {
                    if (contentShown == "FolderScreen"){
                        navController.navigate("folder_screen")
                    }else{
                        onAddClick.invoke()
                    }
                          },
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
                folder = {
                    contentShown = "FolderScreen"
                    update.invoke()
                }

            )
        }
    ) {
        Column(modifier = Modifier.padding(bottom = 130.dp)){ // Wrap the LazyColumn with a Column composable
            Text(

                text = when (contentShown) {
                    "TaskScreen" -> "Notes"
                    "FavoriteScreen" -> "Favorites"
                    "FolderScreen" -> "Folder"
                    else -> ""
                },
              
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
            )
            when(contentShown){
                "TaskScreen" -> {LazyColumn {
                    itemsIndexed(filteredNotes.reversed()) { index, note ->
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clickable {
                                    val noteJson = Gson().toJson(note)
                                    val folderJson = Gson().toJson(user)
                                    navController.navigate("task_item_screen/$noteJson/$index/$folderJson")
                                }


                        ) {
                            NoteItem(
                                note = note,
                                userRepository = userRepository,
                                i = filteredNotes.size - index - 1,
                                update = update,
                                onDeleteClick = { deleteNote(
                                    userRepository,
                                    filteredNotes.size - index - 1,
                                    mainTread = { update.invoke() }) }
                            )

                        } }


                }}
                "FavoriteScreen" -> {LazyColumn {
                    itemsIndexed(filteredNotes.reversed()) { index, note ->
                        if (note.favorite){
                            Box(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .clickable { onAddClick.invoke() }


                            ) {
                                val favoriteIndex = filteredNotes.indexOf(note)
                                NoteItem(
                                    note = note,
                                    userRepository = userRepository,
                                    i = favoriteIndex,
                                    update = update,
                                    onDeleteClick = { deleteNote(
                                        userRepository,
                                        favoriteIndex,
                                        mainTread = { update.invoke() }) }
                                )

                            }
                        }
                    }


                }}
                "FolderScreen" -> {
                    DeleteNoteAlertDialog(showDialog, {
                        showDialog = it
                        update.invoke()
                    }) {
                        folderIndex?.let { it1 -> removeFolder(userRepository, it1){} }
                    }
                    LazyVerticalGrid(columns =  GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ){


                        items(folders.size){
                            folder ->
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .border(BorderStroke(5.dp, color = blueColor), shape = shape)
                                    .clickable { }

                            ) {
                                Box(
                                    Modifier
                                        .size(150.dp)
                                        .clip(shape)
                                        .background(Color.White)
                                        .padding(16.dp)

                                ) {
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.Center),
                                        text = folders[folder].name,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold

                                    )
                                }
                                IconButton(onClick = {
                                    folderIndex = folder
                                    showDialog = true
                                                     },
                                    modifier = Modifier.align(Alignment.BottomEnd)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete note.",
                                        tint = MaterialTheme.colors.onSurface,
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                            }

                        }
                    }
                }
                else -> {}
            }


        }
    }

}
