package com.example.starwarsdocs.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwardocs.R
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.domain.models.PlanetsDomain
import com.example.starwarsdocs.domain.models.StarShipDomain
import com.example.starwarsdocs.ui.components.CharacterList
import com.example.starwarsdocs.ui.components.CustomDialog
import com.example.starwarsdocs.ui.components.SearchBar
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel

// Define the category types for navigation
enum class CharacterCategory {
    CHARACTERS, PLANETS, STARSHIPS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    context: Context,
) {
    LaunchedEffect(true) {
        sharedViewModel.getAllCharacter()
    }
    // Local state for selected category
    var selectedCategory by remember { mutableStateOf(CharacterCategory.CHARACTERS) }

    // Get characters from the ViewModel based on the selected category
    val characters by when (selectedCategory) {
        CharacterCategory.CHARACTERS -> sharedViewModel.allLocalCharacters.collectAsState()
        CharacterCategory.PLANETS -> sharedViewModel.allPlanets.collectAsState()
        CharacterCategory.STARSHIPS -> sharedViewModel.allStarShips.collectAsState()
    }

    val showLoading by sharedViewModel.showLoading.collectAsState()
    val showDialog by sharedViewModel.showDialog.collectAsState()
    val messageError by sharedViewModel.message.collectAsState()

    var query by remember { mutableStateOf("") }

    val filteredCharacters = when (selectedCategory) {
        CharacterCategory.CHARACTERS -> characters?.filter {
            (it as PeopleDomain).name.contains(query, ignoreCase = true)
        }

        CharacterCategory.PLANETS -> characters?.filter {
            (it as PlanetsDomain).name.contains(query, ignoreCase = true)
        }

        CharacterCategory.STARSHIPS -> characters?.filter {
            (it as StarShipDomain).name.contains(query, ignoreCase = true)
        }
    }

    // Trigger fetching characters based on the category
    LaunchedEffect(selectedCategory) {
        when (selectedCategory) {
            CharacterCategory.CHARACTERS -> sharedViewModel.getAllCharacter()
            CharacterCategory.PLANETS -> sharedViewModel.getAllPlanets()
            CharacterCategory.STARSHIPS -> sharedViewModel.getAllStarships()
        }
    }

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
        contentWindowInsets = WindowInsets(16.dp),
        bottomBar = {
            BottomAppBar(
                actions = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            IconButton(onClick = {
                                selectedCategory = CharacterCategory.CHARACTERS
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_people_24),
                                    contentDescription = "People"
                                )
                            }
                            Spacer(modifier = Modifier.width(64.dp))
                            IconButton(onClick = { selectedCategory = CharacterCategory.PLANETS }) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_place_24),
                                    contentDescription = "Planets"
                                )
                            }
                            Spacer(modifier = Modifier.width(64.dp))
                            IconButton(onClick = {
                                selectedCategory = CharacterCategory.STARSHIPS
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_airplanemode_active_24),
                                    contentDescription = "Starships"
                                )
                            }
                        }
                    }
                },
            )
        }
    ) { contentPadding ->
        if (showLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        CustomDialog(
            showDialog = showDialog,
            title = "Info",
            message = messageError,
            confirmButtonText = "Ok",
            cancelButtonText = "Cancel",
            onConfirm = sharedViewModel::onDialogConfirm,
            onCancel = sharedViewModel::onDialogDismiss
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            SearchBar(query = query, onQueryChanged = { query = it })
            Spacer(modifier = Modifier.height(16.dp))
            if (filteredCharacters != null) {
                CharacterList(
                    navController = navController,
                    viewModel = sharedViewModel,
                    items = filteredCharacters
                )
            } else {
                Text(text = context.getString(R.string.emptyList))
            }
        }
    }
}
