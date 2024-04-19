package com.gabodev.recipebook.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabodev.recipebook.data.Result
import com.gabodev.recipebook.data.recipes.RecipesRepository
import com.gabodev.recipebook.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipesRepository: RecipesRepository,
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    var isLoading = mutableStateOf(false)
    private val _recipeMeals = MutableLiveData<Recipe>()
    val recipeMeals: LiveData<Recipe> = _recipeMeals

    private fun searchRecipe() {
        viewModelScope.launch {
            getRecipes()
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        if (_searchText.value.isNotEmpty()) {
            if (_searchText.value.length >= 3) {
                searchRecipe()
            } else if (recipeMeals.value?.meals?.isEmpty() == true) {
                clearSearchText()
            }
        } else {
            clearSearchText()
        }
    }

    fun clearSearchText() {
        _searchText.value = ""
        searchRecipe()
    }

    suspend fun getRecipes() {
        val result =
            if (searchText.value.isEmpty()) {
                recipesRepository.getRecipes()
            } else {
                recipesRepository.getRecipesSearch(searchText.value)
            }
        if (result is Result.Success) {
            isLoading.value = true
            _recipeMeals.value = result.data
        }
    }
}
