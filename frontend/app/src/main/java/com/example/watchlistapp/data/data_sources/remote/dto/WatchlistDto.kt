package com.example.watchlistapp.data.data_sources.remote.dto

import com.example.watchlistapp.domain.models.WatchlistItem
import com.example.watchlistapp.data.data_sources.local.dto.WatchlistItemLocalDto


data class WatchlistDto(
    val idItem: String,
    val title: String,
    val status: String
)


fun WatchlistDto.toLocalDto(): WatchlistItemLocalDto {
    return WatchlistItemLocalDto(
        idItem = idItem,
        title = title,
        status = status
    )
}

// Conversion vers le modèle de domaine
fun WatchlistDto.toWatchlistItem(): WatchlistItem {
    return WatchlistItem(
        idItem = idItem,
        title = title,
        status = status
    )
}
