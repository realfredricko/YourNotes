package com.example.yournotes.feature_note.presentation.notes_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yournotes.feature_note.domain.model.Note
import com.example.yournotes.feature_note.domain.use_cases.NoteUseCase
import com.example.yournotes.feature_note.presentation.util.NoteOrder
import com.example.yournotes.feature_note.presentation.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCase: NoteUseCase):ViewModel(){
    val _state = mutableStateOf(NotesState())
    private var recentlyDeletedNote: Note? =null
    private var getNotesJob: Job?=null

        init{
            getNotes(NoteOrder.Date(OrderType.Descending))
        }
fun onEvent(event: NotesEvent){
    when(event){
        is NotesEvent.Order ->{
if (_state.value.noteOrder::class==event.noteOrder::class
    &&_state.value.noteOrder.orderType==event.noteOrder.orderType){
    return
    }
            getNotes(event.noteOrder)
        }
        is NotesEvent.DeleteNote ->{
            viewModelScope.launch { noteUseCase.deleteNoteUseCase(event.note)
            recentlyDeletedNote=event.note}
        }
        is NotesEvent.RestoreNote ->{
viewModelScope.launch { noteUseCase.addNote(recentlyDeletedNote ?:return@launch)
recentlyDeletedNote =null}
        }
        is NotesEvent.ToggleOrderSection ->{
            _state.value =_state.value.copy(
                isOrderSectionVisible = !_state.value.isOrderSectionVisible
            )

        }
    }

    }
   private fun getNotes(noteOrder: NoteOrder) {
       getNotesJob?.cancel()
       getNotesJob = noteUseCase.getNoteUseCase(noteOrder as NoteOrder.Date)
            .onEach { notes ->
                _state.value=_state.value.copy(
                    notes=notes,
                    noteOrder=noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

}




