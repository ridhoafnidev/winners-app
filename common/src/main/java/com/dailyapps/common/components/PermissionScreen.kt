package com.dailyapps.common.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
    permissinStatus: PermissionStatus,
    onRequestPermission: () -> Unit
) {
    val context = LocalContext.current
    Scaffold {
        Column(modifier = Modifier
            .padding(it)
            .padding(16.dp)) {
            when(permissinStatus) {
                is PermissionStatus.Denied -> {
                    if (permissinStatus.shouldShowRationale) {
                        Toast.makeText(context, "We need permission to continue", Toast.LENGTH_SHORT).show()
                    }
                }
                is PermissionStatus.Granted -> {}
            }

            if (permissinStatus.shouldShowRationale) {
                BaseButton(text = "Click to request permission") {
                    onRequestPermission
                }
            }
        }
    }
}