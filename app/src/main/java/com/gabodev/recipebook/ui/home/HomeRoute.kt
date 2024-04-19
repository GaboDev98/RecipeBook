package com.gabodev.recipebook.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeRoute(
    navController: NavController,
    openDrawer: () -> Unit,
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    HomeScreens(homeViewModel, navController, openDrawer)
}
