package com.example.yournotes.feature_note.presentation.util

sealed class Scren(val route :String){
    object NoteScreen :Scren(route = "notes_screen")
    object AddEditNoteScreen :Scren(route = "add_edit_notes_screen")
}
