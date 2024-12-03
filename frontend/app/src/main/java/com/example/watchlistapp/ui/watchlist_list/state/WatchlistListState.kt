package com.example.watchlistapp.ui.watchlist_list.state

import com.example.watchlistapp.domain.models.WatchlistItem
import com.example.watchlistapp.domain.models.WatchlistDetail


data class WatchlistListState(
    val isLoading: Boolean = false,
    val watchlistItems: List<WatchlistItem> = emptyList(),
    val error: String? = null
)
