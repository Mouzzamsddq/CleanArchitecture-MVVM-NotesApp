package com.example.cleanarchitecturenotesapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecturenotesapp.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }

}