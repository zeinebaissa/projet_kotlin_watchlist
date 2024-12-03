package com.example.watchlistapp.data.data_sources.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.watchlistapp.data.data_sources.remote.dto.WatchlistDto
import com.example.watchlistapp.domain.models.WatchlistItem

@Entity(tableName = "watchlist_items")
data class WatchlistItemLocalDto(
    @PrimaryKey val idItem: String,
    val title: String,
    val status: String
)

// Conversion to domain model
fun WatchlistItemLocalDto.toWatchlistItem(): WatchlistItem {
    return WatchlistItem(
        idItem = idItem,
        title = title,
        status = status
    )
}

// Conversion from domain model
fun WatchlistItem.toLocalDto(): WatchlistItemLocalDto {
    return WatchlistItemLocalDto(
        idItem = idItem,
        title = title,
        status = status
    )
}

// Conversion to remote DTO
fun WatchlistItemLocalDto.toRemoteDto(): WatchlistDto {
    return WatchlistDto(
        idItem = idItem,
        title = title,
        status = status
    )
}
