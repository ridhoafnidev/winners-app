package com.dailyapps.feature.absent.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Geocoder
import android.os.Build
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.dailyapps.feature.absent.AbsentViewModel
import java.util.Locale


val permissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

private var locationCallback: LocationCallback? = null
private var locationRequired = false

@Composable
fun AbsentMaps(
    viewModel: AbsentViewModel
) {
    val context = LocalContext.current
    var currentLocation by remember {
        mutableStateOf(LatLng(0.0, 0.0))
    }
    val geocoder = Geocoder(context, Locale.getDefault())

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 18f)
    }

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                // Update UI with location data
                currentLocation = LatLng(lo.latitude, lo.longitude)
                cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation, 17f)

                try {
                    val address =
                        geocoder.getFromLocation(
                            currentLocation.latitude,
                            currentLocation.longitude,
                            1
                        )

                    if (!address.isNullOrEmpty()) {
                        viewModel.setCurrentAddress(address[0].getAddressLine(0))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }


                viewModel.setLatitude(currentLocation.latitude)
                viewModel.setLongitude(currentLocation.longitude)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (lo.isMock) {
                        viewModel.setIsMock(true)
                    } else {
                        viewModel.setIsMock(false)
                    }
                } else {
                    if (lo.isFromMockProvider) {
                        viewModel.setIsMock(true)
                    } else {
                        viewModel.setIsMock(false)
                    }
                }
            }
        }
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        if (permissionsMap.values.isNotEmpty()) {
            val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
            if (areGranted) {
                locationRequired = true
                startLocationUpdates(fusedLocationClient)
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    }

    if (permissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }) {
        startLocationUpdates(fusedLocationClient)
    } else {
        SideEffect {
            launcherMultiplePermissions.launch(permissions)
        }
    }

    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                myLocationButtonEnabled = true,
                compassEnabled = true
            )
        )
    }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
    }
    GoogleMap(
        modifier = Modifier.fillMaxWidth(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        val schoolLocation = LatLng(1.252067, 101.228550)

        MapMarker(
            context = context,
            position = currentLocation,
            title = "Me",
            iconResourceId = com.dailyapps.common.R.drawable.ic_current_location
        )
        MapMarker(
            context = context,
            position = schoolLocation,
            title = "SMPS Santo Yosef",
            iconResourceId = com.dailyapps.common.R.drawable.ic_office_location
        )
        Circle(
            center = schoolLocation,
            fillColor = Color(0x220000FF),
            radius = 80.0
        )
    }
    Switch(
        checked = uiSettings.zoomControlsEnabled,
        onCheckedChange = {
            uiSettings = uiSettings.copy(zoomControlsEnabled = it)
        }
    )
}

@SuppressLint("MissingPermission")
private fun startLocationUpdates(fusedLocationClient: FusedLocationProviderClient) {
    locationCallback?.let {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            it,
            Looper.getMainLooper()
        )
    }
}

@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    title: String,
    @DrawableRes iconResourceId: Int
) {
    val icon = bitmapDescriptorFromVector(
        context, iconResourceId
    )
    Marker(
        state = MarkerState(position = position),
        title = title,
        icon = icon,
    )
}

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}