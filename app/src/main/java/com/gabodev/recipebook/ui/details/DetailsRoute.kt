package com.gabodev.recipebook.ui.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun DetailsRoute(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
) {
    val detailsViewModel: DetailsViewModel = koinViewModel()
    val uId = navBackStackEntry.arguments?.getString("mealId")
    uId?.let { id ->
        DetailsScreen(id, detailsViewModel, navController)
    }
}
