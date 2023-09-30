package com.example.yournotes.feature_note.domain.model

import androidx.room.Entity
import com.example.yournotes.ui.theme.DarkGray
import com.example.yournotes.ui.theme.LightGreen
import com.example.yournotes.ui.theme.PurpleGrey80
import com.example.yournotes.ui.theme.Red

@Entity
data class Note(
    val title:String,
            val content:String,
                    val timeStamp:Long,
                            val color:Int,
                                    @primaryKey val id:Int?=null){

    companion object{val noteColors=listOf(DarkGray, LightGreen, PurpleGrey80, Red)
    }
}
class InvalidNoteException(message:String):Exception(message)
annotation class primaryKey
