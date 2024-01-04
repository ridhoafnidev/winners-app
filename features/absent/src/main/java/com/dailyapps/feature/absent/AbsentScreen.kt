package com.dailyapps.feature.absent

import android.content.res.Configuration
import android.location.Location
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dailyapps.common.components.BaseAppBar
import com.dailyapps.common.components.BaseButton
import com.dailyapps.common.components.BaseRadioButton
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.components.FontType
import com.dailyapps.common.utils.NavRoute
import com.dailyapps.common.utils.toLocalDate
import com.dailyapps.common.utils.withFormat
import com.dailyapps.feature.absent.components.AbsentMaps
import com.dailyapps.feature.absent.components.DateUtil
import com.dailyapps.feature.absent.state.AbsentScreenState
import com.dailyapps.feature.absent.state.CreateAbsentScreenState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.dailyapps.common.R as commonR

@Composable
fun AbsentScreen(
    navController: NavHostController,
    viewModel: AbsentViewModel
) {
    val context = LocalContext.current
    val createAbsentResult by viewModel.createAbsentScreenState.collectAsState()
    val currentTokenTypeId by viewModel.currentTokenTypeId.collectAsState()
    val masterSchoolYear by viewModel.masterSchoolYear.collectAsState()
    val isMock by viewModel.isMock.collectAsState()
    val absentResult by viewModel.absentScreenState.collectAsState()
    val currentClass by viewModel.currentClass.collectAsState()

    val studentId = currentClass?.id ?: 0

    val (token, tokenType) = currentTokenTypeId

    var isEnabled by rememberSaveable { mutableStateOf(false) }

    val currentSchoolYear by lazy {
        masterSchoolYear.first()
    }

    LaunchedEffect(createAbsentResult) {
        when (createAbsentResult) {
            is CreateAbsentScreenState.Success -> {
                (createAbsentResult as CreateAbsentScreenState.Success).createAbsent.let {
                    Toast.makeText(
                        context,
                        "Absensi telah berhasil dilakukan",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(NavRoute.homeScreen) {
                        popUpTo(NavRoute.homeScreen) {
                            inclusive = true
                        }
                    }
                }
            }

            is CreateAbsentScreenState.Loading -> {

            }

            is CreateAbsentScreenState.Empty -> {

            }

            is CreateAbsentScreenState.Error -> {
                (createAbsentResult as CreateAbsentScreenState.Error).message.let { msg ->
                    Toast.makeText(
                        context,
                        msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    LaunchedEffect(currentTokenTypeId) {
        currentTokenTypeId.apply {
            if (token.isNotEmpty() && tokenType.isNotEmpty() && studentId != 0 && masterSchoolYear.isNotEmpty()) {
                val tahunAjaran = currentSchoolYear.id ?: 0

                viewModel.getAbsentById(
                    token = getToken(currentTokenTypeId),
                    siswaId = studentId,
                    tahunAjaranSemesterId = tahunAjaran
                )
            }
        }
    }

    LaunchedEffect(absentResult) {
        when (absentResult) {
            is AbsentScreenState.Success -> {
                (absentResult as AbsentScreenState.Success).absent.let { absent ->
                    absent.data?.let { absentData ->
                        val data =
                            absentData.filter { it.tanggalAbsensi!!.toLocalDate() withFormat "yyyy-MM-dd" == DateUtil.getCurrentDate() }

                        if (data.isNotEmpty()) {
                            !isEnabled
                        } else {
                            isEnabled = true
                        }
                    }
                }
            }

            is AbsentScreenState.Loading -> {}
            is AbsentScreenState.Error -> {}
            else -> {}
        }
    }


    Scaffold(
        topBar = {
            BaseAppBar(
                title = stringResource(R.string.absent),
                onClickBack = { navController.popBackStack() },
                menuIconResource = com.dailyapps.common.R.drawable.ic_history,
                elevation = 1.dp
            ) {
                Handler(Looper.getMainLooper()).postDelayed({
                    navController.navigate(NavRoute.noteScreen)
                }, 200)
            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(16.dp)
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            Column {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(198.dp)
                )
                {
                    AbsentMaps(
                        viewModel
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = commonR.drawable.ic_map_pin),
                        contentDescription = stringResource(R.string.image_pin_location),
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 8.dp)
                    )
                    BaseText(
                        text = viewModel.currentAddress.collectAsState().value,
                        fontFamily = FontType.LIGHT
                    )
                }

                Row(modifier = Modifier.padding(top = 48.dp)) {
                    BaseText(
                        text = stringResource(R.string.absen),
                        fontSize = 16.sp,
                        fontFamily = FontType.LIGHT,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    BaseText(
                        text = stringResource(R.string.required),
                        fontSize = 16.sp,
                        fontFamily = FontType.LIGHT,
                        fontColor = Color.Red
                    )
                }

                BaseRadioButton(items = listOf("Hadir", "Sakit", "Izin")) {
                    viewModel.setCurrentChooseAbsent(it)
                }
            }

            BaseButton(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(top = 64.dp)
                    .height(56.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd),
                text = stringResource(R.string.submit),
                enabled = isEnabled
            ) {
                if (isEnabled) {
                    val timeNow = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

                    if (timeNow > "06:00:00" && timeNow < "09:00:00") {
                        if (!isMock) {
                            if (viewModel.currentChooseAbsent.value == "Hadir") {
                                val source = Location("")
                                val destination = Location("")

                                source.latitude = viewModel.currentLatitude.value
                                source.longitude = viewModel.currentLongitude.value
                                destination.latitude = 1.252067
                                destination.longitude = 101.228550

                                if (source.distanceTo(destination) < 80) {     // 3000 meters = 3 km
                                    currentTokenTypeId.apply {
                                        if (first.isNotEmpty() && second.isNotEmpty() && masterSchoolYear.isNotEmpty()) {
                                            val tahunAjaran = masterSchoolYear.first().id ?: 0
                                            viewModel.createAbsent(
                                                token = "${currentTokenTypeId.second} ${currentTokenTypeId.first}",
                                                siswaId = studentId,
                                                tahunAjaranSemesterId = tahunAjaran,
                                                jenisAbsensiId = 1
                                            )
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Maaf, saat ini anda tidak berada di sekolah",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                var jenisAbsensiId = 0

                                if (viewModel.currentChooseAbsent.value == "Sakit") {
                                    jenisAbsensiId = 2
                                } else if (viewModel.currentChooseAbsent.value == "Izin") {
                                    jenisAbsensiId = 3
                                }

                                currentTokenTypeId.apply {
                                    if (first.isNotEmpty() && second.isNotEmpty() && masterSchoolYear.isNotEmpty()) {
                                        val tahunAjaran = masterSchoolYear.first().id ?: 0
                                        viewModel.createAbsent(
                                            token = "${currentTokenTypeId.second} ${currentTokenTypeId.first}",
                                            siswaId = studentId,
                                            tahunAjaranSemesterId = tahunAjaran,
                                            jenisAbsensiId = jenisAbsensiId
                                        )
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Maaf, Anda terdeteksi menggunakan Fake GPS",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Maaf, Anda tidak bisa melakukan absensi diluar jam yang ditentukan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {
                    Toast.makeText(
                        context,
                        "Maaf, Anda sudah melakukan absensi hari ini",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}


fun getToken(currentToken: Pair<String, String>) =
    "${currentToken.second} ${currentToken.first}"

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AbsentScreenPreview() {
    val navController = rememberNavController()
    val absentViewModel: AbsentViewModel = hiltViewModel()
    AbsentScreen(navController, absentViewModel)
}