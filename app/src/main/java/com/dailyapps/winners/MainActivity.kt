package com.dailyapps.winners

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.scottyab.rootbeer.RootBeer
import com.dailyapps.common.preventScreenShotFeature
import com.dailyapps.feature.auth.MainViewModel
import com.dailyapps.winners.navigation.AppNavigation
import com.dailyapps.winners.ui.theme.ISekolahTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preventScreenShotFeature()
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isSplash.value
            }
        }
        setContent {
            ISekolahTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
//            val rootBeer = RootBeer(this)
//            if (rootBeer.isRooted) {
//                //we found indication of root
//                Toast.makeText(this, "Maaf HP yang digunakan terdeteksi telah di root", Toast.LENGTH_SHORT).show()
//                this.finish()
//                exitProcess(0)
//            }
//            else {
//                //we didn't find indication of root
//                ISekolahTheme {
//                    // A surface container using the 'background' color from the theme
//                    Surface(
//                        modifier = Modifier.fillMaxSize(),
//                        color = MaterialTheme.colorScheme.background
//                    ) {
//                        AppNavigation()
//                    }
//                }
//            }
        }
    }
}
