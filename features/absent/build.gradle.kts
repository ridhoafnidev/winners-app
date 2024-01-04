import dependencies.addMapsDependencies

plugins {
    plugins.`android-feature-library`
}

android {
    namespace = "com.dailyapps.feature.absent"
}

dependencies {
    addMapsDependencies()
}