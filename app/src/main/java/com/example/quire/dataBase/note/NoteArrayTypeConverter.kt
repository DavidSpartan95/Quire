package com.example.quire.dataBase.note

import androidx.room.TypeConverter
import com.google.gson.Gson

class NoteArrayTypeConverter {
    @TypeConverter
    //The names of these functions are bad and confusing
    fun fromInt(value: String): Array<Note>{
        return Gson().fromJson(value,Array<Note>::class.java)
    }
    @TypeConverter
    fun fromDigiCatArray(array: Array<Note>): String {
        return Gson().toJson(array)
    }
}