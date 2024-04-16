package com.gabodev.recipebook.data.recipes.impl

import com.gabodev.recipebook.data.Result
import com.gabodev.recipebook.data.recipes.RecipesRepository
import com.gabodev.recipebook.model.Recipe
import com.gabodev.recipebook.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipesRepositoryImp : RecipesRepository {
    private val apiService = RetrofitInstance.api

    override suspend fun getRecipes(): Result<Recipe?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getRecipes()
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(IllegalArgumentException(e.message))
            }
        }
    }

    override suspend fun getRecipeById(idMeal: String): Result<Recipe?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getRecipeById(idMeal)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(IllegalArgumentException(e.message))
            }
        }
    }
}
