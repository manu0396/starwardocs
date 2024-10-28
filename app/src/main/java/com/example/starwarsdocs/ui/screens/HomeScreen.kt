package com.example.starwarsdocs.ui.screens

import android.content.Context
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
import com.example.starwarsdocs.ui.components.CharacterList
import com.example.starwarsdocs.ui.components.CustomDialog
import com.example.starwarsdocs.ui.components.SearchBar
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    context: Context
) {
    // Get all characters from the ViewModel
    val characters by sharedViewModel.allLocalCharacters.collectAsState()

    val showLoading by sharedViewModel.showLoading.collectAsState()

    val showDialog by sharedViewModel.showDialog.collectAsState()

    val messageError by sharedViewModel.message.collectAsState()

    // Local state to track the search query
    var query by remember { mutableStateOf("") }

    // Filtered list of characters based on the search query
    val filteredCharacters = characters?.filter {
        it.name.contains(query, ignoreCase = true)
    }

    // Trigger fetching all characters once on launch
    LaunchedEffect(Unit) {
        sharedViewModel.getAllCharacter()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(context.getString(R.string.title)) },
                navigationIcon = { Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "arrow_back",
                    modifier = Modifier.clickable { navController.popBackStack() }
                    )
                }
            )
        },
        contentWindowInsets = WindowInsets(16.dp)
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
            // Search bar to update the query
            SearchBar(query = query, onQueryChanged = { query = it })

            Spacer(modifier = Modifier.height(16.dp))

            // Character list showing the filtered characters
            if (filteredCharacters != null) {
                CharacterList(navController = navController, viewModel = sharedViewModel, characters = filteredCharacters)
            } else {
                Text(text = context.getString(R.string.emptyList))
            }
        }
    }
}
