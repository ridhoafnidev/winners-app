package com.dailyapps.feature.note.state

import com.dailyapps.entity.Note

sealed class NoteScreenState {
    class Success(val notes: List<Note>): NoteScreenState() {
        fun getData() = notes
    }
    class Error(val message: String): NoteScreenState()
    object Empty: NoteScreenState()
    object Loading: NoteScreenState()
}
