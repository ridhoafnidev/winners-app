package com.dailyapps.feature.note.components.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.dailyapps.entity.Note
import com.dailyapps.feature.note.components.ItemNoteHistory

@Composable
fun TabNoteScreen(data: List<Note>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)) {
        items(data) {note ->
            ItemNoteHistory(data = note)
        }
    }
}