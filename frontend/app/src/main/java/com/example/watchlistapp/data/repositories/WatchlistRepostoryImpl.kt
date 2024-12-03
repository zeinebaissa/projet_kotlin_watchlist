package com.example.watchlistapp.data.repositories

import com.example.watchlistapp.data.data_sources.local.dao.WatchlistDao
import com.example.watchlistapp.data.data_sources.local.dto.toLocalDto
import com.example.watchlistapp.data.data_sources.local.dto.toRemoteDto
import com.example.watchlistapp.data.data_sources.local.dto.toWatchlistDetail
import com.example.watchlistapp.data.data_sources.local.dto.toWatchlistItem
import com.example.watchlistapp.data.data_sources.remote.WatchlistApi
import com.example.watchlistapp.data.data_sources.remote.dto.toWatchlistItem
import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.models.WatchlistItem
import com.example.watchlistapp.domain.repositories.WatchlistRepository
import javax.inject.Inject
import com.example.watchlistapp.data.data_sources.remote.dto.toLocalDto
import com.example.watchlistapp.data.data_sources.remote.dto.toWatchlistDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WatchlistRepositoryImpl @Inject constructor(
    private val dao: WatchlistDao, // Local database
    private val api: WatchlistApi  // Remote API
) : WatchlistRepository {

    override suspend fun getAllWatchlistItems(forceRefresh: Boolean): List<WatchlistItem> {
        return try {
            if (forceRefresh) {
                val remoteItems = api.getAllWatchlistItems()
                println("API Response: $remoteItems")
                dao.addWatchlistItems(remoteItems.map { it.toLocalDto() })
                remoteItems.map { it.toWatchlistItem() }
            } else {
                val localItems = dao.getAllWatchlistItems()
                println("Local Data: $localItems")
                localItems.map { it.toWatchlistItem() }
            }
        } catch (e: Exception) {
            println("Error fetching watchlist items: ${e.message}")
            emptyList() // Retourne une liste vide en cas d'erreur
        }
    }






    override suspend fun addWatchlistItem(item: WatchlistDetail) {
        api.addWatchlistItem(item.toLocalDto().toRemoteDto()) // Add remotely
        dao.addWatchlistDetail(item.toLocalDto())            // Sync locally
    }


    override suspend fun updateWatchlistItem(id: String, item: WatchlistDetail) {
        api.updateWatchlistItem(id, item.toLocalDto().toRemoteDto()) // Update remotely
        dao.updateWatchlistDetail(item.toLocalDto())                // Sync locally
    }

    override suspend fun deleteWatchlistItem(id: String) {
        api.deleteWatchlistItem(id)           // Delete remotely
        dao.deleteWatchlistDetailById(id)     // Delete locally
    }
    override suspend fun getWatchlistDetailById(id: String, forceRefresh: Boolean): WatchlistDetail? {
        return withContext(Dispatchers.IO) {
            if (forceRefresh) {
                val remoteDetail = api.getWatchlistItemById(id).toWatchlistDetail()
                dao.addWatchlistDetail(remoteDetail.toLocalDto()) // Sync with local DB
                remoteDetail
            } else {
                dao.getWatchlistDetailById(id)?.toWatchlistDetail()
            }
        }
    }
}
