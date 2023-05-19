package com.example.quire.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.quire.dataBase.folder.Folder
import com.example.quire.dataBase.folder.FolderArrayTypeConverter
import com.example.quire.dataBase.note.Note
import com.example.quire.dataBase.note.NoteArrayTypeConverter

@Entity
data class User(
    val name: String = "DefaultUser",
    @TypeConverters(NoteArrayTypeConverter::class, FolderArrayTypeConverter::class)
    var notes: Array<Note>,
    var folders: Array<Folder>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}