package com.gabodev.recipebook.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gabodev.recipebook.data.AppContainer
import com.gabodev.recipebook.ui.details.DetailsRoute
import com.gabodev.recipebook.ui.details.DetailsViewModel
import com.gabodev.recipebook.ui.home.HomeRoute
import com.gabodev.recipebook.ui.home.HomeViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun RecipeBookNavGraph(
    appContainer: AppContainer,
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
        composable(route = RecipeBookDestinations.HOME_ROUTE) { navBackStackEntry ->
            val homeViewModel: HomeViewModel =
                viewModel(
                    factory =
                        HomeViewModel.provideFactory(
                            recipesRepository = appContainer.recipesRepository,
                        ),
                )
            HomeRoute(
                homeViewModel = homeViewModel,
                navController = navController,
                openDrawer = openDrawer,
            )
        }
        composable(
            "${RecipeBookDestinations.DETAILS_ROUTE}/{mealId}",
            arguments = listOf(navArgument("mealId") { type = NavType.StringType }),
        ) { navBackStackEntry ->
            val detailsViewModel: DetailsViewModel =
                viewModel(
                    factory = DetailsViewModel.provideFactory(appContainer.recipesRepository),
                )
            DetailsRoute(
                detailsViewModel = detailsViewModel,
                navBackStackEntry = navBackStackEntry,
                navController = navController,
                openDrawer = openDrawer,
            )
        }
    }
}
