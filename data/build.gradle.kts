import dependencies.addApiResponseModule
import dependencies.addChuckerDebugDependencies
import dependencies.addCommonModule
import dependencies.addDiModule
import dependencies.addDomainModule

plugins {
    plugins.`android-core-library`
}

android {
    namespace = "com.dailyapps.data"
}

dependencies {
    addApiResponseModule()
    addDiModule()
    addDomainModule()
    addCommonModule()
}