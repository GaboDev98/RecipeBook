package com.gabodev.recipebook.network

import com.gabodev.recipebook.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php?s=")
    suspend fun getRecipes(): Recipe?

    @GET("lookup.php")
    suspend fun getRecipeById(
        @Query("i") id: String,
    ): Recipe?
}
