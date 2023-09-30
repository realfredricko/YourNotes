package com.example.yournotes.feature_note.domain.use_cases

import com.example.yournotes.feature_note.domain.model.Note
import com.example.yournotes.feature_note.domain.repository.NoteRepo
import com.example.yournotes.feature_note.presentation.util.NoteOrder
import com.example.yournotes.feature_note.presentation.util.NoteOrder.*
import com.example.yournotes.feature_note.presentation.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetNoteUseCase(private val repository :NoteRepo) {
    operator fun invoke(noteOrder: Date = Date(OrderType.Descending)): Flow<Any> {

        return repository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is Title-> notes.sortedBy { it.title.lowercase(). }

                        is Date-> {
                            notes.sortedBy { it.timeStamp }
                        }

                        is Color -> notes.sortedBy { it.color }

                        else -> {}
                    }
                }

                is OrderType.Descending -> {
                    when (noteOrder) {
                        is  Title-> notes.sortedByDescending { it.title.lowercase() }
                        is  Date-> notes.sortedByDescending { it.timeStamp }
                        is Color -> notes.sortedByDescending { it.color }
                        else -> {}
                    }
                }

            }
        }
    }
}