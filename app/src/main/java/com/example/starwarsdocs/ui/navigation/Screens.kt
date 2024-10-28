package com.example.starwarsdocs.ui.navigation

sealed class Screen(val route: String){
    data object Home: Screen("home_screen")
    data object Detail: Screen("detail_screen")
    data object Auth: Screen("auth")
}