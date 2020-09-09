package com.ezioapps.notenote.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    val title : String,
    val body : String
): Serializable