package com.example.cleanarchitecturenotesapp.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecturenotesapp.feature_note.data.data_source.NoteDatabase
import com.example.cleanarchitecturenotesapp.feature_note.data.repository.NoteRepositoryImpl
import com.example.cleanarchitecturenotesapp.feature_note.domain.repository.NoteRepository
import com.example.cleanarchitecturenotesapp.feature_note.domain.use_case.AddNoteUseCase
import com.example.cleanarchitecturenotesapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.cleanarchitecturenotesapp.feature_note.domain.use_case.GetNoteUseCase
import com.example.cleanarchitecturenotesapp.feature_note.domain.use_case.GetNotesUseCase
import com.example.cleanarchitecturenotesapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao())
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )

    }
}