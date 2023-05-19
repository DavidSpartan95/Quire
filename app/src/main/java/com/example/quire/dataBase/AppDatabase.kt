package com.example.quire.dataBase

import android.content.Context
import androidx.room.*
import com.example.quire.dataBase.folder.FolderArrayTypeConverter
import com.example.quire.dataBase.note.NoteArrayTypeConverter


@Database(entities = [User::class], version = 1 )
@TypeConverters(NoteArrayTypeConverter::class,FolderArrayTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance (context: Context):
                AppDatabase {
            return INSTANCE ?: synchronized (this) {
                val instance =
                    Room.databaseBuilder (
                        context.applicationContext ,
                        AppDatabase::class.java,
                        "my-app-db"
                    ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}