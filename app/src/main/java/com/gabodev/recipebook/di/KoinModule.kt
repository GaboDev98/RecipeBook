package com.gabodev.recipebook.di

import com.gabodev.recipebook.ui.details.DetailsViewModel
import com.gabodev.recipebook.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainModule: Module =
    module {
    }

val viewModelModule: Module =
    module {
        viewModel {
            HomeViewModel(get())
        }
        viewModel {
            DetailsViewModel(get())
        }
    }
