package com.example.yournotes.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent
    {
    data class TitleEntered(val value:String):AddEditNoteEvent()
        data class ChangeTitleFocus(val focusState: FocusState):AddEditNoteEvent()
        data class ContentEntered(val value:String):AddEditNoteEvent()
        data class ChangeContentFocus(val focusState: FocusState):AddEditNoteEvent()
        data class ColorChange(val value: Int):AddEditNoteEvent()
        object SaveNote:AddEditNoteEvent()
}
