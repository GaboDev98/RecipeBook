package com.gabodev.recipebook.data.recipes

import com.gabodev.recipebook.data.Result
import com.gabodev.recipebook.model.Recipe

interface RecipesRepository {
    suspend fun getRecipes(): Result<Recipe?>

    suspend fun getRecipesSearch(word: String): Result<Recipe?>

    suspend fun getRecipeById(idMeal: String): Result<Recipe?>
}
