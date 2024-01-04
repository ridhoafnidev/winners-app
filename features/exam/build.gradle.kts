import dependencies.addDataModule

plugins {
    plugins.`android-feature-library`
}

android {
    namespace = "com.dailyapps.feature.score"
}

dependencies {
    addDataModule()
}