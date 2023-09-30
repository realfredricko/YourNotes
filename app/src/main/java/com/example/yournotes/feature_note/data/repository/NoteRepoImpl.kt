package com.example.yournotes.feature_note.data.repository

import com.example.yournotes.feature_note.data.data_source.NoteDao
import com.example.yournotes.feature_note.domain.model.Note
import com.example.yournotes.feature_note.domain.repository.NoteRepo
import kotlinx.coroutines.flow.Flow

class NoteRepoImpl (
    private val dao :NoteDao): NoteRepo {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNotesById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
      dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

}