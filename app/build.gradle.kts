import dependencies.*

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id ("dagger.hilt.android.plugin")
    kotlin("kapt")
}


android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdkVersion
    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        vectorDrawables{
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    addCommonModule()
    addFeatureModule()
    addDiModule()
    addDataModule()
    addDomainModule()

    addSplashScreenDependencies()
    addDataStoreDependencies()
    addAndroidComposeDependencies()
    addAndroLifeCycleDependencies()
    addCoroutinesAndroidDependencies()
    addHiltDependencies()
    addNetworkDependencies()
    addLeakcanaryDependencies()
    addAndroidTestsDependencies()
    addRoomDependencies()
    addChuckerDebugDependencies()
    addChuckerReleaseDependencies()
    addRootCheckerDependencies()
}