package com.example.starwarsdocs.ui.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.starwarsdocs.ui.screens.DetailScreen
import com.example.starwarsdocs.ui.screens.DetailTextScreen
import com.example.starwarsdocs.ui.screens.HomeScreen
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SetupNavGraph(context: Context, navController: NavHostController) {
    val sharedViewModel: SharedViewModel = hiltViewModel()
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            // Home Screen
            composable(route = Screen.Home.route) { navBackStackEntry ->
                HomeScreen(
                    navController = navController,
                    sharedViewModel = sharedViewModel,
                    context = context,
                )
            }

            // Detail Screen
            composable(route = Screen.Detail.route) { navBackStackEntry ->
                DetailScreen(
                    navController = navController,
                    sharedViewModel = sharedViewModel,
                    context = context,
                    animatedVisibilityScope = this,
                    onItemClick = { label, text ->
                        navController.navigate(
                            "detail_text/{$label}/{$text}",
                        )
                    }
                )
            }

            //Detail Text
            composable(route = Screen.DetailText.route) { navBackStackEntry ->
                val text = navBackStackEntry.arguments?.getString("text") ?: ""
                val label = navBackStackEntry.arguments?.getString("label") ?: ""
                DetailTextScreen(
                    label = label,
                    text = text,
                    animatedVisibilityScope = this
                )
            }
        }
    }

}

/**
 * Function to instantiate viewmodel, passing route in case it exits.
 */
@Composable
fun NavBackStackEntry.sharedViewModel(navController: NavController): SharedViewModel {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}