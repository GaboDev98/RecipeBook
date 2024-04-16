package com.gabodev.recipebook.data.recipes.impl

import com.gabodev.recipebook.model.Meal
import com.gabodev.recipebook.model.Recipe

val recipes: Recipe =
    Recipe(
        meals =
            listOf(
                Meal(
                    idMeal = "recipe1",
                    strMeal = "Recipe 1",
                    strDrinkAlternate = null,
                ),
                Meal(
                    idMeal = "recipe2",
                    strMeal = "Recipe 2",
                    strDrinkAlternate = null,
                ),
            ),
    )
