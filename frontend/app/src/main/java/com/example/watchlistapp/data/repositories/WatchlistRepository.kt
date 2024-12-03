package com.example.watchlistapp.domain.repositories

import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.models.WatchlistItem

interface WatchlistRepository {
    suspend fun getAllWatchlistItems(forceRefresh: Boolean): List<WatchlistItem>
    suspend fun getWatchlistDetailById(id: String, forceRefresh: Boolean): WatchlistDetail? // Add this line
    suspend fun addWatchlistItem(item: WatchlistDetail)
    suspend fun updateWatchlistItem(id: String, item: WatchlistDetail)
    suspend fun deleteWatchlistItem(id: String)

}
