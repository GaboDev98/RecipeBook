package com.gabodev.recipebook.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gabodev.recipebook.model.Meal
import com.gabodev.recipebook.ui.RecipeBookDestinations

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreens(
    homeViewModel: HomeViewModel,
    navController: NavController,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit, block = {
        homeViewModel.getRecipes()
    })

    TopAppBarCustom(homeViewModel, navController, openDrawer, modifier)
}

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(
    homeViewModel: HomeViewModel,
    navController: NavController,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                title = {
                    Text("Recipe Book")
                },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        ScrollContent(homeViewModel, navController, innerPadding, modifier)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ScrollContent(
    homeViewModel: HomeViewModel,
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Row(
        Modifier.padding(innerPadding)
            .fillMaxSize(),
    ) {
        if (!homeViewModel.isLoading.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        } else {
            val mealsList = homeViewModel.recipeMeals?.meals
            if (mealsList != null) {
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    items(items = mealsList) { item ->
                        ColumnItemMeal(
                            item = item,
                            navController = navController,
                            modifier,
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Not Results",
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ColumnItemMeal(
    item: Meal,
    navController: NavController,
    modifier: Modifier,
) {
    Card(
        modifier
            .padding(10.dp)
            .wrapContentSize(),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Row(
            modifier
                .clickable(onClick = {
                    navController.navigate("${RecipeBookDestinations.DETAILS_ROUTE}/${item.idMeal}")
                })
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            AsyncImage(
                model = item.strMealThumb,
                contentDescription = item.strMeal,
            )
            Column(modifier.padding(12.dp)) {
                Text(
                    text = item.strMeal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Text(
                    text = item.strCategory ?: "",
                    fontSize = 20.sp,
                )
                Text(
                    text = item.strArea ?: "",
                    fontSize = 18.sp,
                )
            }
        }
    }
}
