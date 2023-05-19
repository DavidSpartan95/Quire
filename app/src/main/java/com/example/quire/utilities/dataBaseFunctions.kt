package com.example.quire.utilities

import android.icu.util.Calendar
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.folder.Folder
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

fun setFavorite(userRepository:UserRepository,index: Int,mainTread:() -> Unit){
    userRepository.performDatabaseOperation(Dispatchers.IO){

        userRepository.changeFavoriteStatus(index)
        CoroutineScope(Dispatchers.Main).launch {
            mainTread.invoke()
        }

    }
}

fun addFolder(userRepository: UserRepository, folder:Folder,mainTread: () -> Unit?){
    userRepository.performDatabaseOperation(Dispatchers.IO){

        userRepository.addFolder(folder)
        CoroutineScope(Dispatchers.Main).launch {
            mainTread.invoke()
        }
    }
}

fun removeFolder(userRepository: UserRepository, folderIndex:Int,mainTread: () -> Unit?){
    userRepository.performDatabaseOperation(Dispatchers.IO){

        userRepository.removeFolder(folderIndex)
        CoroutineScope(Dispatchers.Main).launch {
            mainTread.invoke()
        }
    }
}
fun addNoteToFolder(userRepository: UserRepository, folderIndex:Int, note: Note,mainTread: () -> Unit?){
    userRepository.performDatabaseOperation(Dispatchers.IO){

        userRepository.addNoteToFolder(folderIndex, note)
        CoroutineScope(Dispatchers.Main).launch {
            mainTread.invoke()
        }
    }
}
