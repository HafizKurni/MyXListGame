package com.dicoding.myxgame.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object Detail : Screen("home/{gameId}") {
        fun createRoute(gameId: Long) = "home/$gameId"
    }
}
