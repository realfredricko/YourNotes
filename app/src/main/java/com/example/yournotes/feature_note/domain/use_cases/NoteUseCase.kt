package com.example.yournotes.feature_note.domain.use_cases

data class NoteUseCase( val getNoteUseCase: GetNoteUseCase,val deleteNoteUseCase: DeleteNoteUseCase,val addNote: AddNote,
val getNote: GetNote) {
    fun addNote(title: Any, content: Any) {

    }
}
