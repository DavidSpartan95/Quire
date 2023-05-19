package com.example.quire.dataBase.folder

import androidx.room.TypeConverter
import com.google.gson.Gson

class FolderArrayTypeConverter {

    @TypeConverter
    //The names of these functions are bad and confusing
    fun fromInt(value: String): Array<Folder>{
        return Gson().fromJson(value,Array<Folder>::class.java)
    }
    @TypeConverter
    fun fromDigiCatArray(array: Array<Folder>): String {
        return Gson().toJson(array)
    }

}