package dependencies
import core.Dependencies
import org.gradle.api.artifacts.dsl.DependencyHandler

//region compose
fun DependencyHandler.addAndroidComposeDependencies(){
    androidComposeBomDependencies.forEach {
        add("implementation", platform(it))
    }
    androidComposeDependencies.forEach {
        add("implementation",it)
    }
}

//endregion
//region android lifecycle

fun DependencyHandler.addAndroLifeCycleDependencies(){
    androidxLifeCycleDependencies.forEach {
        add("implementation",it)
    }
}

//endregion
//region coroutines

fun DependencyHandler.addCoroutinesAndroidDependencies(){
    coroutinesAndroidDependencies.forEach {
        add("implementation",it)
    }
}

//endregion
//region coil image

fun DependencyHandler.addCoilImageLoadingDependencies(){
    coilImageLoadingDependencies.forEach {
        add("implementation",it)
    }
}

//endregion
//region coil image

fun DependencyHandler.addZoomableImageDependencies(){
    zoomableImageDependencies.forEach {
        add("implementation",it)
    }
}

//endregion
//region network

fun DependencyHandler.addNetworkDependencies(configurationName:String = "implementation"){
    networkDependencies.forEach {
        add(configurationName,it)
    }
}

//endregion
//region chucker

fun DependencyHandler.addChuckerDebugDependencies(configurationName: String = "debugImplementation"){
    chuckerDebugDependencies.forEach {
        add(configurationName, it)
    }
}

fun DependencyHandler.addChuckerReleaseDependencies(configurationName: String = "releaseImplementation") {
    chuckerReleaseDependencies.forEach {
        add(configurationName, it)
    }
}

//endregion
//region hilt

fun DependencyHandler.addHiltDependencies() {
    add("implementation",Dependencies.hiltAndroid)
    add("implementation",Dependencies.hiltNavCompose)
    add("kapt",Dependencies.hiltCompiler)
}

//endregion
//region timber

fun DependencyHandler.addTimberDependencies(configurationName:String = "implementation"){
    add(configurationName,Dependencies.timber)
}

//endregion
//region gson

fun DependencyHandler.addGsonDependencies(configurationName:String = "implementation"){
    add(configurationName,Dependencies.gson)
}

//endregion
//region leakcanary

fun DependencyHandler.addLeakcanaryDependencies(){
    add("debugImplementation",Dependencies.leakcanary)
}

//endregion
//region data store

fun DependencyHandler.addDataStoreDependencies(){
    add("implementation",Dependencies.dataStore)
}

//endregion
//region splash api

fun DependencyHandler.addSplashScreenDependencies(){
    add("implementation",Dependencies.splashScreen)
}

//endregion
//region testing

fun DependencyHandler.addAndroidTestsDependencies() {
    add("testImplementation",Dependencies.jUnit)
    add("androidTestImplementation",Dependencies.jUnitTestUi)
    add("androidTestImplementation",Dependencies.jUnitExt)
    add("debugImplementation",Dependencies.composeTooling)
    add("debugImplementation",Dependencies.composeTestManifest)
    add("androidTestImplementation",Dependencies.espresso)
}

//endregion
//region room

val roomLibraries = listOf(
    Dependencies.roomRuntime,
    Dependencies.roomKtx
)
val roomKaptLibraries = listOf(
    Dependencies.roomCompiler
)
fun DependencyHandler.addRoomDependencies() {
    implementation(roomLibraries)
    kapt(roomKaptLibraries)
}

//endregion
//region maps

val mapsLibraries = listOf(
    Dependencies.maps,
    Dependencies.mapServices,
    Dependencies.serviceLocation,
)

fun DependencyHandler.addMapsDependencies() {
    implementation(mapsLibraries)
}

//endregion
//region system ui

val systemUiLibrary = listOf(
    Dependencies.systemUiController
)

fun DependencyHandler.addSystemUiControllerDependencies() {
    implementation(systemUiLibrary)
}

//endregion
//region root checker

val rootChecker = listOf(
    Dependencies.root_checker
)

fun DependencyHandler.addRootCheckerDependencies() {
    implementation(rootChecker)
}

//endregion
//region deps extentions

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.implementationPlatform(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", platform(dependency))
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

//endregion
