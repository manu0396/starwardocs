package com.example.starwarsdocs.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.domain.models.PlanetsDomain
import com.example.starwarsdocs.domain.models.StarShipDomain
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel


@Composable
fun CharacterList(navController: NavController, viewModel: SharedViewModel, items: List<Any>) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        if (items.isEmpty()) {
            Text(text = "No characters found", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()) // Adding scroll behavior
                    .size(LocalConfiguration.current.screenHeightDp.dp)
            ) {
                items(items.size) { index ->
                    when(items[index]){
                        is PeopleDomain -> {
                            CharacterItem(navController = navController, viewModel = viewModel, character = items[index] as PeopleDomain)
                        }

                        is StarShipDomain -> {
                            StarShipItem(navController = navController, viewModel = viewModel, character = items[index] as StarShipDomain)
                        }

                        is PlanetsDomain -> {
                            PlanetsItem(navController = navController, viewModel = viewModel, character = items[index] as PlanetsDomain)
                        }
                    }
                }
            }
        }
    }
}