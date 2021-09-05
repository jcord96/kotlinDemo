package es.jco.demo.presentation.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.jco.data.source.LocalDataSource
import es.jco.data.source.RemoteDataSource
import es.jco.demo.data.database.AppRoomDatabase
import es.jco.demo.data.database.RoomConstants
import es.jco.demo.data.database.RoomDataSource
import es.jco.demo.data.server.APIService
import es.jco.demo.data.server.ServerConstants
import es.jco.demo.data.server.ServerDataSource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    // Provides for data server

    @Singleton
    @Provides
    fun provideOkkHttpClient(): OkHttpClient {
        var client = OkHttpClient.Builder().build()

        return client
    }

    @Singleton
    @Provides
    fun provideRetrofit(okkHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ServerConstants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okkHttpClient)
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit) : APIService {
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: APIService): RemoteDataSource {
        return ServerDataSource(apiService)
    }

    // Provides for local database

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) : AppRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            AppRoomDatabase::class.java,
            RoomConstants.DBNAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(appRoomDatabase: AppRoomDatabase): RoomDataSource {
        return RoomDataSource(appRoomDatabase)
    }
}