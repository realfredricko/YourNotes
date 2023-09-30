package com.example.yournotes.feature_note.presentation.notes_screen

import com.example.yournotes.feature_note.presentation.util.NoteOrder
import com.example.yournotes.feature_note.presentation.util.OrderType

data class NotesState(
    val notes: Any = emptyList(),
    val noteOrder:NoteOrder =NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean=false
)
