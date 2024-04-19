package com.gabodev.recipebook.di

import com.gabodev.recipebook.data.recipes.RecipesRepository
import com.gabodev.recipebook.data.recipes.impl.RecipesRepositoryImp
import com.gabodev.recipebook.network.ApiService
import com.gabodev.recipebook.ui.details.DetailsViewModel
import com.gabodev.recipebook.ui.home.HomeViewModel
import com.gabodev.recipebook.utils.Constants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule: Module =
    module {
        single {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
        single<RecipesRepository> {
            RecipesRepositoryImp(get())
        }
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
