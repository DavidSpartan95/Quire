package com.example.quire.utilities

import android.icu.util.Calendar
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat

fun addNewNote(title:String,content:String,userRepository: UserRepository,mainTread:() -> Unit){
    val calendar = Calendar.getInstance().time
    val date = DateFormat.getDateInstance().format(calendar)
    userRepository.performDatabaseOperation(Dispatchers.IO){
        try {
            userRepository.addNote(Note(title,content ,tag = "nothing important", date = date))
            CoroutineScope(Dispatchers.Main).launch {
                mainTread.invoke()
            }
        }
        catch (e: Exception){
            println(e)
        }
    }
}

fun deleteNote(userRepository:UserRepository,index: Int,mainTread:() -> Unit){
    userRepository.performDatabaseOperation(Dispatchers.IO){
        try {
            userRepository.removeNote(index)
            CoroutineScope(Dispatchers.Main).launch {
                mainTread.invoke()
            }
        }
        catch (e: Exception){
            println(e)
        }
    }
}