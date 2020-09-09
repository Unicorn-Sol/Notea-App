package com.ezioapps.notenote.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao

    companion object{

        @Volatile private var instance : NoteDatabase? = null

        fun getAppDataBase(context: Context): NoteDatabase? {
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "myDB").build()
                }
            }
            return instance
        }


    }
}