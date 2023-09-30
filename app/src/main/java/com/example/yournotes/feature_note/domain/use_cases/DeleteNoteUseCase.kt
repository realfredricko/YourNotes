package com.example.yournotes.feature_note.domain.use_cases

import com.example.yournotes.feature_note.domain.model.Note
import com.example.yournotes.feature_note.domain.repository.NoteRepo

class DeleteNoteUseCase(private val repository :NoteRepo) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}