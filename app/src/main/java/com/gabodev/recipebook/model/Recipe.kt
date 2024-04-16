package com.gabodev.recipebook.model

data class Recipe(
    val meals: List<Meal>,
)

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String? = null,
    val strCategory: String? = null,
    val strArea: String? = null,
    val strInstructions: String? = null,
    val strMealThumb: String? = null,
    val strTags: String? = null,
)
