package es.jco.demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import es.jco.demo.presentation.ui.detail.DetailViewModel
import es.jco.demo.presentation.ui.main.MainViewModel
import es.jco.demo.presentation.ui.splashscreen.SplashScreenViewModel
import es.jco.usecases.*

/**
 * Splash screen activity module
 * This class declares how to provide the view model
 *
 * @constructor Create empty Splash screen activity module
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class SplashScreenActivityModule {

    @Provides
    fun splashScreenViewModelProvider(loadUsersUseCase: LoadUsersUseCase) = SplashScreenViewModel(loadUsersUseCase)
}

/**
 * Main activity module
 * This class declares how to provide the view model
 *
 * @constructor Create empty Main activity module
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {

    @Provides
    fun mainViewModelProvider(getUsersUseCase: GetUsersUseCase, deleteUserByIdUseCase: DeleteUserByIdUseCase) = MainViewModel(getUsersUseCase, deleteUserByIdUseCase)
}

/**
 * Detail activity module
 * This class declares how to provide the view model
 *
 * @constructor Create empty Detail activity module
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class DetailActivityModule {

    @Provides
    fun detailViewModelProvider(getUserByIdUseCase: GetUserByIdUseCase, saveUserUseCase: SaveUserUseCase) = DetailViewModel(getUserByIdUseCase, saveUserUseCase)
}