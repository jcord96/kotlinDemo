package es.jco.demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.jco.data.repository.UserRepository
import es.jco.usecases.GetUsersUseCase
import es.jco.usecases.LoadUsersUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun loadUsersUseCaseProvider(userRepository: UserRepository) = LoadUsersUseCase(userRepository)

    @Provides
    fun getUsersUseCaseProvider(userRepository: UserRepository) = GetUsersUseCase(userRepository)
}