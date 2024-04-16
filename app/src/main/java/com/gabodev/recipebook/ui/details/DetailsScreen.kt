package com.gabodev.recipebook.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gabodev.recipebook.model.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun DetailsScreen(
    idMeal: String,
    detailsViewModel: DetailsViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    LaunchedEffect(Unit, block = {
        detailsViewModel.getRecipesById(idMeal)
    })

    if (!detailsViewModel.isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    } else {
        val mealDetails = detailsViewModel.recipeDetails?.meals?.first()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = mealDetails?.strMeal ?: "",
                    navigationIconContent = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
        ) { innerPadding ->
            if (mealDetails != null) {
                MealContent(mealDetails, modifier, innerPadding)
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    title: String,
    navigationIconContent: @Composable () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior?,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = navigationIconContent,
        scrollBehavior = scrollBehavior,
        modifier = modifier,
    )
}

private val defaultSpacerSize = 16.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun MealContent(
    meal: Meal,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier.padding(horizontal = defaultSpacerSize),
        state = state,
    ) {
        mealContentItems(meal)
    }
}

fun LazyListScope.mealContentItems(meal: Meal) {
    item {
        MealHeaderImage(meal)
        Spacer(Modifier.height(defaultSpacerSize))
        Text(meal.strMeal, style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(8.dp))
        if (meal.strDrinkAlternate != null) {
            Text(meal.strDrinkAlternate, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(defaultSpacerSize))
        }
        if (meal.strInstructions != null) {
            Text(meal.strInstructions, style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(defaultSpacerSize))
        }
    }
    // item { PostMetadata(meal.metadata, Modifier.padding(bottom = 24.dp)) }
    // items(meal.paragraphs) { Paragraph(paragraph = it) }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun MealHeaderImage(meal: Meal) {
    val imageModifier =
        Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
    AsyncImage(
        model = meal.strMealThumb,
        contentDescription = meal.strMeal,
        modifier = imageModifier,
        contentScale = ContentScale.Crop,
    )
}
