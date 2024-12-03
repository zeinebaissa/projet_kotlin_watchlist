package com.example.watchlistapp.ui.watchlist_detail.state

import com.example.watchlistapp.domain.models.WatchlistDetail

data class WatchlistDetailState(
    val detail: WatchlistDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
