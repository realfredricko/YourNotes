package com.example.yournotes.feature_note.data.data_source

import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities =[ContactsContract.CommonDataKinds.Note::class],
    version =1
)
 abstract class NoteDatabase : RoomDatabase() {
abstract val noteDao : NoteDao
companion object{
    const val DATABASE_NAME="notes_db"
}
}