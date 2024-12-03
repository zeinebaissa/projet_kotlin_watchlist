package com.example.watchlistapp.data.data_sources.remote.dto

import com.example.watchlistapp.data.data_sources.local.dto.WatchlistDetailLocalDto
import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.models.WatchlistItem

data class WatchlistDetailDto(
    val idItem: String,   // Unique identifier for the item
    val title: String,    // Title of the item
    val type: String,     // Type of the item (e.g., "Movie", "TV Show")
    val status: String,   // Current status of the item (e.g., "Pending", "Watched")
    val comment: String?  // Optional comment about the item
)


fun WatchlistDetailDto.toWatchlistItem(): WatchlistItem {
    return WatchlistItem(
        idItem = idItem,
        title = title,
        status = status
    )
}

// Conversion to domain model
fun WatchlistDetailDto.toWatchlistDetail(): WatchlistDetail {
    return WatchlistDetail(
        idItem = idItem,
        title = title,
        type = type,
        status = status,
        comment = comment
    )
}
// Conversion to Local DTO
fun WatchlistDetailDto.toLocalDto(): WatchlistDetailLocalDto {
    return WatchlistDetailLocalDto(
        idItem = idItem,
        title = title,
        type = type,
        status = status,
        comment = comment
    )
}
