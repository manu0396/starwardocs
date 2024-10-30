package com.example.starwarsdocs.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel

@Composable
fun CharacterItem(navController: NavController, viewModel: SharedViewModel, character: PeopleDomain) {
    Card (
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 20.dp
        ),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        shape = RoundedCornerShape(10),
        modifier = Modifier
            .padding(bottom = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .clickable {
                    viewModel.navigateToDetailCharacter(navController, character)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "name: " + character.name)
            Text(text = "url: " + character.url)
            Text(text = "homeworld: " + character.homeworld)
            Spacer(modifier = Modifier.size(50.dp))
        }
    }

}