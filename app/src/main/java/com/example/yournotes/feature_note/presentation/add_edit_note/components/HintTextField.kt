package com.example.yournotes.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun HintTextField(
    text:String,
    hint:String,
    isHintVisible:Boolean=true,
    modifier: Modifier=Modifier,
    onValueChange:(String)->Unit,
    textStyle: TextStyle= TextStyle(), singleLine:Boolean=false,
    onFocusChange:(FocusState) ->Unit
) {
    Box(modifier = Modifier) {
        BasicTextField(value = text, onValueChange = onValueChange, singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) })
Text(text = hint, style = textStyle, color =Color.DarkGray)
    }
}


}