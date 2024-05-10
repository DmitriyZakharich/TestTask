package com.example.testtasktutu.di

import android.content.Context
import com.example.data.RepositoryManagerImpl
import com.example.data.database.AppDatabaseImpl
import com.example.data.database.DatabaseHelper
import com.example.data.interfaces.AppDatabase
import com.example.data.interfaces.NetworkLoader
import com.example.data.network.NetworkLoaderImpl
import com.example.data.network.RetrofitHelper
import com.example.data.utils.ConnectionManager
import com.example.domain.repository.RepositoryManager
import com.example.domain.usecases.GetDetailsUseCase
import com.example.domain.usecases.GetListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepositoryManager(
        networkLoader: NetworkLoader,
        appDatabase: AppDatabase,
        connectionManager: ConnectionManager
    ): RepositoryManager =
        RepositoryManagerImpl(networkLoader, appDatabase, connectionManager)

    @Provides
    fun provideGetListUseCase(repositoryManager: RepositoryManager): GetListUseCase =
        GetListUseCase(repositoryManager)

    @Provides
    fun provideGetDataUseCase(repositoryManager: RepositoryManager): GetDetailsUseCase =
        GetDetailsUseCase(repositoryManager)

    @Provides
    fun provideNetworkLoader(retrofitHelper: RetrofitHelper): NetworkLoader =
        NetworkLoaderImpl(retrofitHelper)

    @Provides
    fun provideAppDatabase(databaseHelper: DatabaseHelper): AppDatabase =
        AppDatabaseImpl(databaseHelper)

    @Provides
    fun provideConnectionManager(@ApplicationContext appContext: Context): ConnectionManager =
        ConnectionManager(appContext)

    @Provides
    @Singleton
    fun provideDatabaseHelper(@ApplicationContext appContext: Context): DatabaseHelper =
        DatabaseHelper(appContext)

    @Provides
    @Singleton
    fun provideRetrofitHelper(): RetrofitHelper =
        RetrofitHelper()
}