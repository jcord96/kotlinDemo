package es.jco.demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.jco.data.repository.UserRepository
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource

/**
 * Data module
 * This class declares all providers to inject the repositories
 *
 * @constructor Create empty Data module
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun userRepositoryProvider(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): UserRepository = UserRepository(localDataSource, remoteDataSource)
}