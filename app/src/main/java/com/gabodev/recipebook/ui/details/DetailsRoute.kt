package com.gabodev.recipebook.ui.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Suppress("ktlint:standard:function-naming")
@Composable
fun DetailsRoute(
    detailsViewModel: DetailsViewModel,
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
) {
    val uId = navBackStackEntry.arguments?.getString("mealId")
    uId?.let { id ->
        DetailsScreen(id, detailsViewModel, navController)
    }
}
