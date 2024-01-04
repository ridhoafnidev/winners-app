import dependencies.addApiResponseModule

plugins {
    plugins.`android-feature-library`
}

android {
    namespace = "com.dailyapps.feature.auth"
}

dependencies {
    addApiResponseModule(configurationName = "api")
}