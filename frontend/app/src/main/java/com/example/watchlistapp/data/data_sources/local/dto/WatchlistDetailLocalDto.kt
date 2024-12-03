package com.example.watchlistapp.data.data_sources.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.watchlistapp.data.data_sources.remote.dto.WatchlistDetailDto
import com.example.watchlistapp.domain.models.WatchlistDetail

@Entity(tableName = "watchlist_details")
data class WatchlistDetailLocalDto(
    @PrimaryKey val idItem: String,
    val title: String,
    val type: String,
    val status: String,
    val comment: String?
)

// Conversion to domain model
fun WatchlistDetailLocalDto.toWatchlistDetail(): WatchlistDetail {
    return WatchlistDetail(
        idItem = idItem,
        title = title,
        type = type,
        status = status,
        comment = comment
    )
}

// Conversion from domain model
fun WatchlistDetail.toLocalDto(): WatchlistDetailLocalDto {
    return WatchlistDetailLocalDto(
        idItem = idItem ?: throw IllegalArgumentException("idItem cannot be null"),
        title = title,
        type = type,
        status = status,
        comment = comment
    )
}

// Conversion to remote DTO
fun WatchlistDetailLocalDto.toRemoteDto(): WatchlistDetailDto {
    return WatchlistDetailDto(
        idItem = idItem,
        title = title,
        type = type,
        status = status,
        comment = comment
    )
}
