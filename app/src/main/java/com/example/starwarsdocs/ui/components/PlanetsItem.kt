package com.example.starwarsdocs.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarsdocs.domain.models.PlanetsDomain
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel

@Composable
fun PlanetsItem(navController: NavController, viewModel: SharedViewModel, character: PlanetsDomain) {
    Row{
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth()
                .wrapContentSize()
                .clickable {
                    Log.d("com.example.starwardocs", "SelectedCharacter(CharacterItem): $character")
                    viewModel.navigateToDetailPlanet(navController, character)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "name: " + character.name)
            Text(text = "url: " + character.url)
            Text(text = "population: " + character.population)
            Spacer(modifier = Modifier.size(50.dp))
        }
    }

}