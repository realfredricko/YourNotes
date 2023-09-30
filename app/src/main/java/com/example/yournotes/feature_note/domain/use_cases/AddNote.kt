package com.example.yournotes.feature_note.domain.use_cases

import com.example.yournotes.feature_note.domain.model.InvalidNoteException
import com.example.yournotes.feature_note.domain.model.Note
import com.example.yournotes.feature_note.domain.repository.NoteRepo
import kotlin.jvm.Throws

class AddNote (
    private val repository:NoteRepo) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {

        if (note.title.isBlank()) {
            throw InvalidNoteException("Title is empty!")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content cannot be  empty!")
        }
        repository.insertNote(note)
    }
}