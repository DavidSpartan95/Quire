package com.example.quire.ui.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.utilities.addNewNote
import com.example.quire.utilities.deleteNote

@Composable
fun FavoriteComp(notes:Array<Note>, userRepository: UserRepository) {
    LazyColumn {
        itemsIndexed(notes) { index,note ->
            if (note.favorite){
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()

                ) {
                    NoteItem(note = note, userRepository = userRepository, i = index, update = {}, onDeleteClick = {})
                }
            }
        }
            }

}