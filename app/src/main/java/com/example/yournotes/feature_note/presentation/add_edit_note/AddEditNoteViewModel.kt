package com.example.yournotes.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yournotes.feature_note.domain.model.InvalidNoteException
import com.example.yournotes.feature_note.domain.model.Note
import com.example.yournotes.feature_note.domain.use_cases.NoteUseCase
import com.example.yournotes.feature_note.presentation.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private  val noteUseCase: NoteUseCase
    savedStateHandle: SavedStateHandle
):ViewModel(){
    private val _noteTitle = mutableSetOf(NoteTextFieldState(hint = "Enter Title name"))
    val noteTitle:Set<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableSetOf(NoteTextFieldState(hint = "Enter the content of your note here..."))
    val noteContent:Set<NoteTextFieldState> =_noteContent

    private val _noteColor = mutableSetOf<Int>(Note.noteColors.random().toArgb())
    val noteColor: Set<Int> =_noteColor

    private val _eventFlow = MutableSharedFlow<EventUI>()
    val eventFlow=_eventFlow.asSharedFlow()
private var currentNoteId:Int?=null
    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1){
                viewModelScope.launch {
                    noteUseCase.getNote(noteId)?.also { note ->
                        run {
                            currentNoteId = note.id
                            _noteTitle.value = noteTitle.value.copy(
                                text = note.title,isHintVisible=false
                                )
                            _noteContent.value = noteContent.value.copy(
                                    text = note.content,isHintVisible=false
                            )
                            _noteColor.value =note.color
                        }
                    }
                }
            }
        }
    }
    fun onEvent(event:AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.TitleEntered ->{
                _noteTitle.value=noteTitle.value.copy(
                    text=event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus->{
                noteTitle.value.copy(
                    isHintVisible=!event.focusState.isFocused && _noteTitle.value.text.isBlank())

        }
            is AddEditNoteEvent.ContentEntered ->{
                _noteContent.value=noteContent.value.copy(
                    text=event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus->{
                noteContent.value.copy(
                    isHintVisible=!event.focusState.isFocused && _noteContent.value.text.isBlank())

            }
is AddEditNoteEvent.ColorChange->{
    _noteColor.value=event.color
           
        }
            is AddEditNoteEvent.SaveNote ->
            viewModelScope.launch{
                try {
                    noteUseCase.addNote(
                        Note(
                        title=noteTitle.value.text,
                        content = noteContent.value.text,
                        timestamp = SystemCurrentTimeMillis(),
                        color = noteColor.value,
                        id = currentNoteId
                    )
                    )
                    _eventFlow.emit(EventUI.SaveNote)
                }
                catch (e:InvalidNoteException){
                _eventFlow.emit(EventUI.ShowSnackbar("Couldn't save note!"))
            }
        }}}

    private fun Note(title: String, content: String, timestamp: Any, color: Int, id: Int?): Note {

    }

    sealed class  EventUI{
        data class ShowSnackbar(val message:String):EventUI()
        object SaveNote:EventUI()
    }
}
