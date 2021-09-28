package es.jco.demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.jco.data.repository.UserRepository
import es.jco.usecases.*

/**
 * Use case module
 * This class declares all providers to inject the use cases
 *
 * @constructor Create empty Use case module
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun loadUsersUseCaseProvider(userRepository: UserRepository) = LoadUsersUseCase(userRepository)

    @Provides
    fun getUserByIdUseCaseProvider(userRepository: UserRepository) = GetUserByIdUseCase(userRepository)

    @Provides
    fun getUsersUseCaseProvider(userRepository: UserRepository) = GetUsersUseCase(userRepository)

    @Provides
    fun saveUserUseCaseProvider(userRepository: UserRepository) = SaveUserUseCase(userRepository)

    @Provides
    fun deleteUserByIdUseCaseProvider(userRepository: UserRepository) = DeleteUserByIdUseCase(userRepository)
}