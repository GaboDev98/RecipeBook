package com.gabodev.recipebook.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    navController: NavController,
    openDrawer: () -> Unit,
) {
    HomeScreens(homeViewModel, navController, openDrawer)
}
