package es.jco.demo.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import es.jco.demo.presentation.ui.detail.DetailViewModel
import es.jco.demo.presentation.ui.main.MainViewModel
import es.jco.demo.presentation.ui.splashscreen.SplashScreenViewModel

@Module
@InstallIn(ActivityRetainedComponent::class)
class SplashScreenActivityModule {

    @Provides
    fun splashScreenViewModelProvider() = SplashScreenViewModel()
}

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {

    @Provides
    fun mainViewModelProvider() = MainViewModel()
}

@Module
@InstallIn(ActivityRetainedComponent::class)
class DetailActivityModule {

    @Provides
    fun detailViewModelProvider() = DetailViewModel()
}