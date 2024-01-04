package dependencies
import core.Dependencies

internal val androidComposeBomDependencies = listOf(
    Dependencies.composeBom,
)

internal val androidComposeDependencies = listOf(
    Dependencies.coreKtx,
    Dependencies.composeGraphics,
    Dependencies.composeMaterialYou,
    Dependencies.composeActivity,
    Dependencies.composeUi,
    Dependencies.materialIconCore,
    Dependencies.composePreviewUi,
    Dependencies.materialIconExtended,
    Dependencies.composeConstraintLayout,
    Dependencies.composeNavigation,
    Dependencies.composeAnimatedNavigation,
    Dependencies.composePager,
    Dependencies.composePagerIndicator,
    Dependencies.systemUiController,
    Dependencies.accompanistPermission
)

internal val androidxLifeCycleDependencies = listOf(
    Dependencies.viewModel,
    Dependencies.liveData,
    Dependencies.lifecycleRuntime,
    Dependencies.viewModelSaveState,
    Dependencies.lifecycleService,
)

internal val coroutinesAndroidDependencies = listOf(
    Dependencies.kotlinCoroutines,
)

internal val coilImageLoadingDependencies = listOf(
    Dependencies.coil,
)

internal val zoomableImageDependencies = listOf(
    Dependencies.zoomableImage,
)

internal val networkDependencies = listOf(
    Dependencies.retrofit,
    Dependencies.retrofitGsonConverter,
    Dependencies.gson,
    Dependencies.okhHttp3,
    Dependencies.okhHttp3Interceptor,
    Dependencies.rxJava3adapter,
)

internal val chuckerDebugDependencies = listOf(
    Dependencies.chucker_debug,
)

internal val chuckerReleaseDependencies = listOf(
    Dependencies.chucker_release,
)

internal val rootCheckerDependencies = listOf(
    Dependencies.root_checker,
)
