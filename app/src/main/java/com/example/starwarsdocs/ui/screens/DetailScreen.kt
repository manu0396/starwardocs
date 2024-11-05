package com.example.starwarsdocs.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwardocs.R
import com.example.starwarsdocs.ui.components.DetailItem
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    context: Context,
    navController: NavController,
    sharedViewModel: SharedViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (String, String) -> Unit
) {

    val selectedCharacter by sharedViewModel.selectedCharacter.collectAsState()
    val selectPlanet by sharedViewModel.selectedPlanet.collectAsState()
    val selectStarship by sharedViewModel.selectedStarship.collectAsState()

    Log.d("com.example.starwardocs", "SelectedCharacter(DetailScreen): $selectedCharacter")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(context.getString(R.string.title)) },
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = "arrow_back",
                        modifier = Modifier.clickable { navController.popBackStack() }
                    )
                }
            )
        },
        contentWindowInsets = WindowInsets(16.dp)
    ) { contentPadding ->
        // A scrollable column to show all details
        LazyColumn(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .size(LocalConfiguration.current.screenHeightDp.dp)
                .fillMaxSize()  // Take the full screen size
                .padding(contentPadding),  // Add padding for spacing
            horizontalAlignment = Alignment.Start // Align text to start
        ) {
            // Individual characteristic items
            if (selectedCharacter != null) {
                item {
                    DetailItem(
                        label = "Name",
                        text = selectedCharacter?.name ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Birth Year",
                        text = selectedCharacter?.birth_year ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Created", text = selectedCharacter?.created ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Edited", text = selectedCharacter?.edited ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Eye Color",
                        text = selectedCharacter?.eye_color ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Films",
                        text = selectedCharacter?.films?.joinToString(", ") ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Gender", text = selectedCharacter?.gender ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Hair Color",
                        text = selectedCharacter?.hair_color ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Height", text = selectedCharacter?.height ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Homeworld",
                        text = selectedCharacter?.homeworld ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Mass", text = selectedCharacter?.mass ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Skin Color",
                        text = selectedCharacter?.skin_color ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Species",
                        text = selectedCharacter?.species?.joinToString(", ") ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Starships",
                        text = selectedCharacter?.starships?.joinToString(", ") ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Vehicles",
                        text = selectedCharacter?.vehicles?.joinToString(", ") ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
            } else if (selectPlanet != null) {
                //Planets details
                item {
                    DetailItem(
                        label = "Name",
                        text = selectPlanet?.name ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Edited",
                        text = selectPlanet?.edited ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Created", text = selectPlanet?.create ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Url", text = selectPlanet?.url ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Climate",
                        text = selectPlanet?.climate ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Films",
                        text = selectPlanet?.films?.joinToString(", ") ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Diameter", text = selectPlanet?.diameter ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Gravity",
                        text = selectPlanet?.gravity ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Orbital period",
                        text = selectPlanet?.orbital_period ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Population",
                        text = selectPlanet?.population ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Rotation period",
                        text = selectPlanet?.rotation_period ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Surface Water",
                        text = selectPlanet?.surface_water ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Residents",
                        text = selectPlanet?.residents?.joinToString(", ") ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Terrain",
                        text = selectPlanet?.terrain ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
            } else if (selectStarship != null) {
                //StarshipsDetails
                item {
                    DetailItem(
                        label = "Name",
                        text = selectStarship?.name ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Edited",
                        text = selectStarship?.edited ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "URL",
                        text = selectStarship?.url ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Created",
                        text = selectStarship?.created ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "MGLT",
                        text = selectStarship?.MGLT ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Cargo Capacity",
                        text = selectStarship?.cargo_capacity ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel,
                    )
                }
                item {
                    DetailItem(
                        label = "Consumables",
                        text = selectStarship?.consumables ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Cost In Credits",
                        text = selectStarship?.cost_in_credits ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Crew",
                        text = selectStarship?.crew ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Hyperdrive Rating",
                        text = selectStarship?.hyperdrive_rating ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Lenght",
                        text = selectStarship?.lenght ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Manufacturer",
                        text = selectStarship?.manufacturer ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Max Atmosphering Speed",
                        text = selectStarship?.max_atmosphering_speed ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Passengers",
                        text = selectStarship?.passengers ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Pilots",
                        text = selectStarship?.pilots ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
                item {
                    DetailItem(
                        label = "Starship Class",
                        text = selectStarship?.startship_class ?: "",
                        animatedVisibilityScope = animatedVisibilityScope,
                        onItemClick = onItemClick,
                        viewModel = sharedViewModel
                    )
                }
            } else {
                item { Text(text = "No item was found", textAlign = TextAlign.Center) }
            }
        }
    }
}
