package com.example.starwarsdocs.ui.screens

import android.content.Context
import android.util.Log
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.example.starwardocs.R
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.ui.components.DetailItem
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(context: Context, navController: NavController, sharedViewModel: SharedViewModel) {

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
                        value = selectedCharacter?.name ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Birth Year",
                        value = selectedCharacter?.birth_year ?: ""
                    )
                }
                item { DetailItem(label = "Created", value = selectedCharacter?.created ?: "") }
                item { DetailItem(label = "Edited", value = selectedCharacter?.edited ?: "") }
                item {
                    DetailItem(
                        label = "Eye Color",
                        value = selectedCharacter?.eye_color ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Films",
                        value = selectedCharacter?.films?.joinToString(", ") ?: ""
                    )
                }
                item { DetailItem(label = "Gender", value = selectedCharacter?.gender ?: "") }
                item {
                    DetailItem(
                        label = "Hair Color",
                        value = selectedCharacter?.hair_color ?: ""
                    )
                }
                item { DetailItem(label = "Height", value = selectedCharacter?.height ?: "") }
                item {
                    DetailItem(
                        label = "Homeworld",
                        value = selectedCharacter?.homeworld ?: ""
                    )
                }
                item { DetailItem(label = "Mass", value = selectedCharacter?.mass ?: "") }
                item {
                    DetailItem(
                        label = "Skin Color",
                        value = selectedCharacter?.skin_color ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Species",
                        value = selectedCharacter?.species?.joinToString(", ") ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Starships",
                        value = selectedCharacter?.starships?.joinToString(", ") ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Vehicles",
                        value = selectedCharacter?.vehicles?.joinToString(", ") ?: ""
                    )
                }
            } else if (selectPlanet != null) {
                //Planets details
                item {
                    DetailItem(
                        label = "Name",
                        value = selectPlanet?.name ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Edited",
                        value = selectPlanet?.edited ?: ""
                    )
                }
                item { DetailItem(label = "Created", value = selectPlanet?.create ?: "") }
                item { DetailItem(label = "Url", value = selectPlanet?.url ?: "") }
                item {
                    DetailItem(
                        label = "Climate",
                        value = selectPlanet?.climate ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Films",
                        value = selectPlanet?.films?.joinToString(", ") ?: ""
                    )
                }
                item { DetailItem(label = "Diameter", value = selectPlanet?.diameter ?: "") }
                item {
                    DetailItem(
                        label = "Gravity",
                        value = selectPlanet?.gravity ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Orbital period",
                        value = selectPlanet?.orbital_period ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Population",
                        value = selectPlanet?.population ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Rotation period",
                        value = selectPlanet?.rotation_period ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Surface Water",
                        value = selectPlanet?.surface_water ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Residents",
                        value = selectPlanet?.residents?.joinToString(", ") ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Terrain",
                        value = selectPlanet?.terrain ?: ""
                    )
                }
            } else if (selectStarship != null) {
                //StarshipsDetails
                item {
                    DetailItem(
                        label = "Name",
                        value = selectStarship?.name ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Edited",
                        value = selectStarship?.edited ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "URL",
                        value = selectStarship?.url ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Created",
                        value = selectStarship?.created ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "MGLT",
                        value = selectStarship?.MGLT ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Cargo Capacity",
                        value = selectStarship?.cargo_capacity ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Consumables",
                        value = selectStarship?.consumables ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Cost In Credits",
                        value = selectStarship?.cost_in_credits ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Crew",
                        value = selectStarship?.crew ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Hyperdrive Rating",
                        value = selectStarship?.hyperdrive_rating ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Lenght",
                        value = selectStarship?.lenght ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Manufacturer",
                        value = selectStarship?.manufacturer ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Max Atmosphering Speed",
                        value = selectStarship?.max_atmosphering_speed ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Passengers",
                        value = selectStarship?.passengers ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Pilots",
                        value = selectStarship?.pilots ?: ""
                    )
                }
                item {
                    DetailItem(
                        label = "Starship Class",
                        value = selectStarship?.startship_class ?: ""
                    )
                }
            } else {
                item { Text(text = "No item was found", textAlign = TextAlign.Center) }
            }
        }
    }
}
