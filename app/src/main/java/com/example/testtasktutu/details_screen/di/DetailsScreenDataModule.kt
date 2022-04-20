package com.example.testtasktutu.details_screen.di

import com.example.testtasktutu.details_screen.data.database.DetailsDatabase
import com.example.testtasktutu.details_screen.data.network.DetailsInfoLoaderImpl
import dagger.Module
import dagger.Provides

@Module
class DetailsScreenDataModule {
    @Provides
    fun getDetailsDatabase(): DetailsDatabase = DetailsDatabase()

    @Provides
    fun provideDetailsDatabase(): DetailsInfoLoaderImpl = DetailsInfoLoaderImpl()
}