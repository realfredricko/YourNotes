package com.example.yournotes.di

import android.app.Application
import androidx.room.Room
import com.example.yournotes.feature_note.data.data_source.NoteDatabase
import com.example.yournotes.feature_note.data.repository.NoteRepoImpl
import com.example.yournotes.feature_note.domain.repository.NoteRepo
import com.example.yournotes.feature_note.domain.use_cases.AddNote
import com.example.yournotes.feature_note.domain.use_cases.DeleteNoteUseCase
import com.example.yournotes.feature_note.domain.use_cases.GetNote
import com.example.yournotes.feature_note.domain.use_cases.GetNoteUseCase
import com.example.yournotes.feature_note.domain.use_cases.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepo{
        return NoteRepoImpl(db.noteDao)
    }
    @Provides
    @Singleton
    fun provideNoteUseCase(repository:NoteRepo):NoteUseCase{
        return NoteUseCase(getNoteUseCase = GetNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository), addNote = AddNote(repository),
                getNote =GetNote(repository))

    }
}