package com.dailyapps.common

import android.app.Activity
import android.view.WindowManager

fun Activity.preventScreenShotFeature() {
    window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
}