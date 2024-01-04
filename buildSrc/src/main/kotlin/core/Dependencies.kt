package core

internal object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeMaterialYou = "androidx.compose.material3:material3:${Versions.materialVersion}"
    const val composeGraphics = "androidx.compose.ui:ui-graphics:${Versions.composeUiVersion}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.composeUiVersion}"
    const val materialIconCore = "androidx.compose.material:material-icons-core"
    const val materialIconExtended = "androidx.compose.material:material-icons-extended"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
    const val composePager = "com.google.accompanist:accompanist-pager:${Versions.pagerComposeVersion}"
    const val composePagerIndicator = "com.google.accompanist:accompanist-pager-indicators:${Versions.pagerIndicatorComposeVersion}"

    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
    const val composeAnimatedNavigation = "com.google.accompanist:accompanist-navigation-animation:${Versions.composeAnimatedNavigationVersion}"
    const val composePreviewUi = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUiVersion}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleVersion}"
    const val viewModelSaveState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleVersion}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:2.6.1"
    const val lifecycleService = "androidx.lifecycle:lifecycle-service:${Versions.lifecycleVersion}"

    val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltNavCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationComposeVersion}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    // datastore preferences
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    // splashscreen
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"

    // system ui controller
    const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiController}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val rxJava3adapter = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val okhHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3Version}"
    const val okhHttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Version}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"

    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanaryVersion}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coilVersion}"
    const val zoomableImage = "net.engawapg.lib:zoomable:${Versions.zoomableImage}"

    const val jUnit = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExtVersion}"
    const val jUnitTestUi = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.composeVersion}"

    // room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomRuntime}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtx}"

    //map
    const val maps = "com.google.maps.android:maps-compose:${Versions.maps}"
    const val mapServices = "com.google.android.gms:play-services-maps:${Versions.mapsServices}"
    const val serviceLocation = "com.google.android.gms:play-services-location:${Versions.serviceLocation}"

    //region chucker

    const val chucker_debug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chucker_release = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"

    //endregion
    //region accompanish permission

    const val accompanistPermission = "com.google.accompanist:accompanist-permissions:${Versions.accompanishPermission}"

    //endregion
    //region root

        const val root_checker = "com.scottyab:rootbeer-lib:${Versions.rootChecker}"

    //endregion
}