package com.gabodev.recipebook.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabodev.recipebook.data.Result
import com.gabodev.recipebook.data.recipes.RecipesRepository
import com.gabodev.recipebook.model.Recipe

class DetailsViewModel(
    private val recipesRepository: RecipesRepository,
) : ViewModel() {
    var isLoading = mutableStateOf(false)
    private val _recipeDetails = MutableLiveData<Recipe>()
    val recipeDetails: LiveData<Recipe> = _recipeDetails

    suspend fun getRecipesById(idMeal: String) {
        val result = recipesRepository.getRecipeById(idMeal)
        if (result is Result.Success) {
            isLoading.value = true
            _recipeDetails.value = result.data
        }
    }
}
