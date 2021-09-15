package es.jco.demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import es.jco.demo.presentation.ui.detail.DetailViewModel
import es.jco.demo.presentation.ui.main.MainViewModel
import es.jco.demo.presentation.ui.splashscreen.SplashScreenViewModel
import es.jco.usecases.GetUsersUseCase
import es.jco.usecases.LoadUsersUseCase

@Module
@InstallIn(ActivityRetainedComponent::class)
class SplashScreenActivityModule {

    @Provides
    fun splashScreenViewModelProvider(loadUsersUseCase: LoadUsersUseCase) = SplashScreenViewModel(loadUsersUseCase)
}

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {

    @Provides
    fun mainViewModelProvider(getUsersUseCase: GetUsersUseCase) = MainViewModel(getUsersUseCase)
}

@Module
@InstallIn(ActivityRetainedComponent::class)
class DetailActivityModule {

    @Provides
    fun detailViewModelProvider() = DetailViewModel()
}