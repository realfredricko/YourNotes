package com.example.yournotes.feature_note.domain.use_cases

import com.example.yournotes.feature_note.domain.model.Note
import com.example.yournotes.feature_note.domain.repository.NoteRepo

class GetNote
    (private val repository: NoteRepo){

    suspend operator fun invoke(id:Int): Note? {
        return repository.getNotesById(id)
    }
}