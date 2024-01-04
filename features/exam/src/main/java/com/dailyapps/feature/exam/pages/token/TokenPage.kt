package com.dailyapps.feature.exam.pages.token

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dailyapps.common.components.BaseAppBar
import com.dailyapps.common.components.BaseButton
import com.dailyapps.common.components.BaseDialog
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.components.BaseTextField
import com.dailyapps.common.components.FontType
import com.dailyapps.common.utils.NavRoute

@Composable
fun TokenPage(
    navController: NavHostController,
    tokenPageViewModel: TokenPageViewModel,
    token: String,
    authToken: String,
    ujianId: Int,
    siswaId: Int,
    ujianEnd: String,
    matpel: String,
) {

    val changeStudentExamsStatusResponse by tokenPageViewModel.changeStudentExamsStatusResponse.collectAsState()
    val inputToken by tokenPageViewModel.token.collectAsState()
    val buttonSubmitEnableState by tokenPageViewModel.buttonSubmitEnableState.collectAsState(initial = false)
    val buttonSubmitLoadingState by tokenPageViewModel.buttonSubmitLoadingState.collectAsState()
    val tokenInvalid by tokenPageViewModel.tokenInvalid.collectAsState()

    val onButtonSubmitClicked: () -> Unit = {
        if (token == inputToken) {
            tokenPageViewModel.updateButtonSubmitLoadingState(true)
            tokenPageViewModel.changeStudentExamsStateToAttempt(authToken, siswaId, ujianId)
        } else {
            tokenPageViewModel.updateTokenInvalid(true)
            tokenPageViewModel.updateToken("")
        }
    }

    val closeInvalidTokenDialog: () -> Unit = {
        tokenPageViewModel.updateTokenInvalid(false)
    }

    LaunchedEffect(key1 = changeStudentExamsStatusResponse) {
        if (changeStudentExamsStatusResponse is TokenPageScreenState.Success) {
            navController.navigate(route = "${NavRoute.testExamScreen}/$authToken/$ujianId/$siswaId/$ujianEnd/$matpel")
        }
    }

    if (tokenInvalid) {
        BaseDialog(
            title = "Token tidak valid!",
            description = "Silahkan masukan kembali token ujian yang valid!",
            onConfirmClicked = closeInvalidTokenDialog,
            confirmButtonText = "Masukan Token Ujian Kembali",
            cancelButtonVisible = false
        )
    }

    if (changeStudentExamsStatusResponse is TokenPageScreenState.Error) {
        BaseDialog(
            title = "Terjadi kesalahan!",
            description = "Tekan tombol coba lagi, untuk melanjutkan!",
            onConfirmClicked = onButtonSubmitClicked,
            confirmButtonText = "Coba lagi",
            cancelButtonVisible = false
        )
    }

    Scaffold(
        topBar = {
            BaseAppBar(
                title = "Mata Pelajaran".uppercase(),
                subTitle = matpel,
                onClickBack = {navController.popBackStack()},
                elevation = 1.dp){}
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            TokenExam(
                token = inputToken,
                onTokenChanged = tokenPageViewModel::updateToken,
                tokenButtonEnabled = buttonSubmitEnableState,
                tokenButtonLoading = buttonSubmitLoadingState,
                onButtonClicked = onButtonSubmitClicked
            )
        }
    }
}

@Composable
fun TokenExam(
    token: String,
    onTokenChanged: (String) -> Unit = {},
    tokenButtonEnabled: Boolean = false,
    tokenButtonLoading: Boolean = false,
    onButtonClicked: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp, bottom = 16.dp),
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .padding(horizontal = 16.dp)
            ) {
                BaseText(
                    text = "Masukkan Token terlebih dahulu untuk lanjut ke halaman ujian.",
                    fontFamily = FontType.MEDIUM,
                    fontSize = 16.sp,
                    fontColor = Color.Black
                )
                Spacer(modifier = Modifier.padding(top= 20.dp))
                BaseTextField(
                    value = token,
                    placeholder = "Masukan Token",
                    onValueChange = onTokenChanged,
                    keyboardType = KeyboardType.Password,
                )
            }
        }
        BaseButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(56.dp)
                .fillMaxWidth()
            ,
            text = "Lanjut",
            enabled = tokenButtonEnabled,
            isLoading = tokenButtonLoading,
            onClick = onButtonClicked
        )
    }
}

/*
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, name = "Light")
@Composable
fun TokenPagePreview() {
    val navHostController = rememberNavController()
    ISekolahTheme() {
        TokenPage(navHostController)
    }
}*/
