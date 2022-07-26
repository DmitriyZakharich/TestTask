package com.example.testtasktutu.screens.details_screen.di

import com.example.testtasktutu.app_repository.di.AppDataModule
import com.example.testtasktutu.screens.details_screen.presentation.DetailsFragment
import dagger.Component

@Component(modules = [DetailsScreenModule::class, DetailsScreenDomainModule::class, AppDataModule::class])
interface DetailsScreenComponent {
    fun inject(detailsFragment: DetailsFragment)
}