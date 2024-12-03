package com.example.watchlistapp.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object WatchlistList : Screen("watchlist_list")

    object WatchlistDetail : Screen("watchlist_detail/{itemId}/{mode}") {
        fun createRoute(itemId: String, mode: String): String = "watchlist_detail/$itemId/$mode"

        val arguments = listOf(
            navArgument("itemId") { type = NavType.StringType },
            navArgument("mode") { type = NavType.StringType }
        )
    }
}
