package com.gabodev.recipebook.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Destinations used in the [RecipeBookApp].
 */
object RecipeBookDestinations {
    const val HOME_ROUTE = "home"
    const val DETAILS_ROUTE = "details"
}

/**
 * Models the navigation actions in the app.
 */
class RecipeBookNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(RecipeBookDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
