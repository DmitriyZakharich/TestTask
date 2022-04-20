package com.example.testtasktutu.details_screen.di

import com.example.testtasktutu.details_screen.presentation.DetailsFragment
import dagger.Component

@Component(
    modules = [DetailsScreenModule::class, DetailsScreenDomainModule::class, DetailsScreenDataModule::class])
interface DetailsScreenComponent {
    fun inject(detailsFragment: DetailsFragment)
}