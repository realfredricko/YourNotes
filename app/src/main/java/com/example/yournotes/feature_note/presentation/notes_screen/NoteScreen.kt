package com.example.yournotes.feature_note.presentation.notes_screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.yournotes.feature_note.presentation.notes_screen.components.NoteItem
import com.example.yournotes.feature_note.presentation.notes_screen.components.OrderSection
import com.example.yournotes.feature_note.presentation.util.Scren
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NoteViewModel= hiltViewModel()) {
    val state = viewModel._state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(Scren.AddEditNoteScreen.route)}, backgroundColor = MaterialTheme.colors.primary) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add your note")
            }
        },
        scaffoldState = scaffoldState
    )

    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Notes",
                    style = MaterialTheme.typography.h4
                )
                IconButton(onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSection) },

                    ) {
                    Icon(contentDescription ="Change order", imageVector = Icons.Default.Done )
                }
            }
            AnimatedVisibility(visible = state.isOrderSectionVisible,
                enter= fadeIn()+ slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChanger = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier=Modifier.fillMaxSize()){
                items(state.notes as Int){
                    note ->
                    NoteItem(note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
navController.navigate(Scren.AddEditNoteScreen.route +"?noteId={noteId}&noteColor={noteColor}")
                            }
                    ) {
                        viewModel.onEvent(NotesEvent.DeleteNote(note))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Note deleted",
                                actionLabel = "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(NotesEvent.RestoreNote)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}