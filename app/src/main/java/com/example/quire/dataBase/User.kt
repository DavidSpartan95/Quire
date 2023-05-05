package com.example.quire.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.quire.dataBase.note.Note
import com.example.quire.dataBase.note.NoteArrayTypeConverter

@Entity
data class User(
    val name: String = "DefaultUser",
    @TypeConverters(NoteArrayTypeConverter::class)
    var notes: Array<Note>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}