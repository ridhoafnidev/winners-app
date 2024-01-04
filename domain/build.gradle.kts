import dependencies.addEntityModule

plugins {
    plugins.`android-core-library`
}

android {
    namespace = "com.dailyapps.domain"
}

dependencies {
    addEntityModule(configurationName = "api")
}