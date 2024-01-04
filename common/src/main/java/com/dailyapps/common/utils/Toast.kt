package com.dailyapps.common.utils
import android.content.Context
import android.widget.Toast

object Toast {
    fun show(context: Context ,message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}