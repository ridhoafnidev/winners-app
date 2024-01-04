package com.dailyapps.feature.note.state

import com.dailyapps.entity.Absent

sealed class AbsentScreenState {
    class Success(val absent: Absent): AbsentScreenState()
    class Error(val message: String): AbsentScreenState()
    object Loading: AbsentScreenState()
    object Empty: AbsentScreenState()
}
