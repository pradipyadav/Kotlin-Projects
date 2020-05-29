package com.sony.mynotes.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(

    var title: String,
    var note: String
) :Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}