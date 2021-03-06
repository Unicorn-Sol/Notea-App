package com.ezioapps.notenote.db

import androidx.room.*


@Dao
interface NoteDao {


    @Insert
    suspend fun addNote(note: Note)

    @Query("select * from Note order by id desc")
    suspend fun getAllNotes() : List<Note>

    @Insert
    suspend fun addMultipleNotes(vararg note :Note)

    @Update
    suspend fun updateNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)
}