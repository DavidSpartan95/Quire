package com.example.quire.dataBase

import androidx.room.*
import com.example.quire.dataBase.folder.Folder
import com.example.quire.dataBase.note.Note


@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateExistingUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE name = :name")
    fun getUserByName(name: String = "DefaultUser"): User?

    @Transaction
    fun addNoteToUser(note: Note){
        val user = getUserByName() ?: return
        user.notes += note

        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }
    @Transaction
    fun removeNoteAtIndex(index: Int) {
        val user = getUserByName() ?: return
        user.notes = user.notes.filterIndexed { i, _ -> i != index }.toTypedArray()
        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }
    @Transaction
    fun addFolderToUser(folder: Folder){
        val user = getUserByName() ?: return
        user.folders += folder

        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }
    @Transaction
    fun removeFolderAtIndex(index: Int) {
        val user = getUserByName() ?: return
        user.folders = user.folders.filterIndexed { i, _ -> i != index }.toTypedArray()
        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }
    @Transaction
    fun addNoteToFolderAtIndex(folderIndex: Int, note: Note) {
        val user = getUserByName() ?: return
        val folders = user.folders

        if (folderIndex >= 0 && folderIndex < folders.size) {
            val folder = folders[folderIndex]
            folder.noteArray += note
        }

        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }
    @Transaction
    fun changeTitle(newTitle: String, index: Int) {
        val user = getUserByName() ?: return
        user.notes[index].title = newTitle
        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }
    @Transaction
    fun changeContent(newContent: String, index: Int) {
        val user = getUserByName() ?: return
        user.notes[index].content = newContent
        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }
    @Transaction
    fun changeTag(newTag: String, index: Int) {
        val user = getUserByName() ?: return
        user.notes[index].tag = newTag
        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }
    @Transaction
    fun changeFavorite(index: Int) {
        val user = getUserByName() ?: return
        user.notes[index].favorite = !user.notes[index].favorite


        if (user.id == null) {
            // If the user doesn't have an ID assigned, insert a new row in the table.
            insertUser(user)
        } else {
            // If the user already has an ID assigned, update the existing row in the table.
            updateExistingUser(user)
        }
    }


}