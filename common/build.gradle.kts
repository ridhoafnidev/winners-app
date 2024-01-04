import dependencies.addCoilImageLoadingDependencies
import dependencies.addEntityModule
import dependencies.addZoomableImageDependencies

plugins {
    plugins.`android-base-library`
}
android {
    buildTypes {
        release {
            buildConfigField("String", "VERSION_NAME", "\"${AppConfig.versionName}\"")
        }
        debug {
            buildConfigField("String", "VERSION_NAME", "\"${AppConfig.versionName}\"")
        }
    }
    namespace = "com.dailyapps.common"
}

dependencies {
    addEntityModule()
    addCoilImageLoadingDependencies()
    addZoomableImageDependencies()
}