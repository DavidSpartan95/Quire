package com.example.quire.utilities

import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun addNewNote(userRepository: UserRepository,mainTread:() -> Unit){
    userRepository.performDatabaseOperation(Dispatchers.IO){
        try {
            userRepository.addNote(Note("Test Title","1. Milk\n2. Bread\n3. Eggs", tag = "nothing important"))
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