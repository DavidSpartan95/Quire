package com.example.quire.dataBase

import com.example.quire.dataBase.folder.Folder
import com.example.quire.dataBase.note.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserRepository (private val appDatabase : AppDatabase, private val
coroutineScope : CoroutineScope ) {

    fun addUser(user: User){
        appDatabase.userDao().insertUser(user)
    }
    fun addNote(note: Note){
        appDatabase.userDao().addNoteToUser(note)
    }
    fun removeNote(index: Int){
        appDatabase.userDao().removeNoteAtIndex(index)
    }
    fun getUserInfo(): List<User> {
        return appDatabase.userDao().getAllUsers()
    }
    fun changeFavoriteStatus(i: Int) {
        appDatabase.userDao().changeFavorite(i)
    }

    fun editExitingNote(i: Int, content:String, title:String){
        appDatabase.userDao().changeContent(content,i)
        appDatabase.userDao().changeTitle(title,i)
    }
    fun addNoteToFolder(folderIndex: Int, note: Note){
        appDatabase.userDao().addNoteToFolderAtIndex(folderIndex,note)
    }
    fun addFolder(folder: Folder){
        appDatabase.userDao().addFolderToUser(folder)
    }
    fun removeFolder(folderIndex: Int){
        appDatabase.userDao().removeFolderAtIndex(folderIndex)
    }
    fun performDatabaseOperation (dispatcher: CoroutineDispatcher,
                                  databaseOperation : suspend () -> Unit) {
        coroutineScope .launch(dispatcher) {
            databaseOperation ()
        }
    }
}