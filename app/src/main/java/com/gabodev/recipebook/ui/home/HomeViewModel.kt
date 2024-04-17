package com.gabodev.recipebook.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabodev.recipebook.data.Result
import com.gabodev.recipebook.data.recipes.RecipesRepository
import com.gabodev.recipebook.model.Recipe

class HomeViewModel(
    private val recipesRepository: RecipesRepository,
) : ViewModel() {
    var isLoading = mutableStateOf(false)
    private val _recipeMeals = MutableLiveData<Recipe>()
    val recipeMeals: LiveData<Recipe> = _recipeMeals

    suspend fun getRecipes() {
        val result = recipesRepository.getRecipes()
        if (result is Result.Success) {
            isLoading.value = true
            _recipeMeals.value = result.data
        }
    }

    companion object {
        fun provideFactory(recipesRepository: RecipesRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(recipesRepository) as T
                }
            }
    }
}
