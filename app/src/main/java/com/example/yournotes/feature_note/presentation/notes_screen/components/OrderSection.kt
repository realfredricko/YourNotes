package com.example.yournotes.feature_note.presentation.notes_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yournotes.feature_note.presentation.util.NoteOrder
import com.example.yournotes.feature_note.presentation.util.OrderType

@Composable
fun OrderSection(modifier: Modifier=Modifier,
                 noteOrder: NoteOrder=NoteOrder.Date(orderType = OrderType.Descending),
                 onOrderChanger:(NoteOrder)->Unit){
    Column(modifier=modifier) {
        Row(modifier=Modifier.fillMaxWidth()) {
          DefaultRadioButton(
              text = "Title", onSelect = noteOrder is NoteOrder.Title,
              onCheck = { onOrderChanger(NoteOrder.Title(noteOrder.orderType)) })
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row (modifier=Modifier.fillMaxWidth()){
            DefaultRadioButton(
                text = "Date", onSelect = noteOrder is NoteOrder.Date,
                onCheck = { onOrderChanger(NoteOrder.Title(noteOrder.orderType)) })
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row (modifier=Modifier.fillMaxWidth()){
            DefaultRadioButton(
                text ="Color", onSelect = noteOrder is NoteOrder.Color,
                onCheck = { onOrderChanger(NoteOrder.Title(noteOrder.orderType)) })
        }
        Spacer(modifier =Modifier.width(16.dp))
        Row (modifier=Modifier.fillMaxWidth()){
            DefaultRadioButton(
                text = "Ascending",
                onSelect = noteOrder.orderType is OrderType.Ascending,
                onCheck = {onOrderChanger(noteOrder.copy(OrderType.Ascending))
        })
        Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                onSelect = noteOrder.orderType is OrderType.Descending,
                onCheck = {
                    onOrderChanger(noteOrder.copy(OrderType.Descending))})

        }
        }
    }



