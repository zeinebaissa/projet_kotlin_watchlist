package com.example.watchlistapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.watchlistapp.ui.theme.WatchlistAppTheme
import com.example.watchlistapp.ui.watchlist_list.ui_elements.WatchlistListScreen
import com.example.watchlistapp.ui.watchlist_detail.ui_elements.WatchlistDetailScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.watchlistapp.ui.watchlist_detail.state.WatchlistDetailViewModel
import com.example.watchlistapp.ui.watchlist_list.state.WatchlistListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchlistApp()
        }
    }
}

@Composable
fun WatchlistApp() {
    WatchlistAppTheme {
        val navController = rememberNavController()
        AppNavigation(navController = navController)
    }
}
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.WatchlistList.route
    ) {
        // Watchlist List Screen
        composable(Screen.WatchlistList.route) {
            val viewModel: WatchlistListViewModel = hiltViewModel()

            WatchlistListScreen(
                viewModel = viewModel,
                onItemClick = { itemId, mode ->
                    // Vérifiez que createRoute génère une route valide
                    val route = Screen.WatchlistDetail.createRoute(itemId, mode)
                    println("Navigating to: $route") // Ajoutez ce log pour déboguer
                    navController.navigate(route)
                },
                onDeleteClick = { itemId ->
                    println("Delete clicked for item: $itemId")
                    viewModel.deleteItem(itemId)
                },
                onStatusToggle = { itemId, newStatus ->
                    println("Status toggled for item: $itemId, new status: $newStatus")
                    viewModel.toggleItemStatus(itemId, newStatus)
                }
            )
        }

        // Watchlist Detail Screen
        composable(
            route = Screen.WatchlistDetail.route,
            arguments = Screen.WatchlistDetail.arguments
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "new"
            val mode = backStackEntry.arguments?.getString("mode") ?: "info"
            println("Opening detail screen: itemId=$itemId, mode=$mode") // Log pour débogage
            val viewModel: WatchlistDetailViewModel = hiltViewModel()

            WatchlistDetailScreen(
                itemId = itemId,
                mode = mode,
                viewModel = viewModel,
                onSaveClick = {
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
