package com.example.watchlistapp.ui.watchlist_detail.ui_elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.watchlistapp.ui.watchlist_detail.state.WatchlistDetailViewModel
import com.example.watchlistapp.domain.models.WatchlistDetail
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
@Composable
fun WatchlistDetailScreen(
    itemId: String,
    mode: String,
    viewModel: WatchlistDetailViewModel,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    val state = viewModel.state.value

    LaunchedEffect(itemId) {
        viewModel.loadWatchlistDetail(itemId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Text(
                    text = "Error: ${state.error}",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
            state.detail != null -> {
                WatchlistDetailContent(
                    detail = state.detail,
                    mode = mode,
                    onSaveClick = { updatedDetail ->
                        viewModel.saveWatchlistDetail(updatedDetail)
                        onSaveClick()
                    },
                    onCancelClick = onCancelClick
                )
            }
        }
    }
}
