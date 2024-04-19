package com.gabodev.recipebook.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gabodev.recipebook.ui.details.DetailsRoute
import com.gabodev.recipebook.ui.home.HomeRoute

@Suppress("ktlint:standard:function-naming")
@Composable
fun RecipeBookNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = RecipeBookDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = RecipeBookDestinations.HOME_ROUTE) {
            HomeRoute(
                navController = navController,
                openDrawer = openDrawer,
            )
        }
        composable(
            "${RecipeBookDestinations.DETAILS_ROUTE}/{mealId}",
            arguments = listOf(navArgument("mealId") { type = NavType.StringType }),
        ) { navBackStackEntry ->
            DetailsRoute(
                navBackStackEntry = navBackStackEntry,
                navController = navController,
            )
        }
    }
}
