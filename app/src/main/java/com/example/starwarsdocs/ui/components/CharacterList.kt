package com.example.starwarsdocs.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel

@Composable
fun CharacterList(navController: NavController, viewModel: SharedViewModel, characters: List<PeopleDomain>) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        if (characters.isEmpty()) {
            Text(text = "No characters found", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()) // Adding scroll behavior
                    .size(LocalConfiguration.current.screenHeightDp.dp)
            ) {
                items(characters.size) { character ->
                    CharacterItem(navController = navController, viewModel = viewModel, character = characters[character])
                }
            }
        }
    }
}