import dependencies.addTimberDependencies

plugins {
    plugins.`android-core-library`
}

android {
    namespace = "com.dailyapps.di"
}

dependencies {
    addTimberDependencies(configurationName = "api")
}