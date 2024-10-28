package com.example.starwarsdocs.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel

@Composable
fun CharacterItem(navController: NavController, viewModel: SharedViewModel, character: PeopleDomain) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize()
                .clickable {
                    Log.d("com.example.starwardocs", "SelectedCharacter(CharacterItem): $character")
                    viewModel.navigateToDetail(navController, character)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "name: " + character.name)
            Text(text = "url: " + character.url)
            Text(text = "homeworld: " + character.homeworld)
            Spacer(modifier = Modifier.size(50.dp))
        }
    }

}