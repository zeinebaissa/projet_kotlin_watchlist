package com.example.watchlistapp.data.data_sources.local.dao

import androidx.room.*
import com.example.watchlistapp.data.data_sources.local.dto.WatchlistDetailLocalDto
import com.example.watchlistapp.data.data_sources.local.dto.WatchlistItemLocalDto
@Dao
interface WatchlistDao {

    // Fetch all items
    @Query("SELECT * FROM watchlist_items")
    suspend fun getAllWatchlistItems(): List<WatchlistItemLocalDto>

    // Fetch item by ID
    @Query("SELECT * FROM watchlist_items WHERE idItem = :id")
    suspend fun getWatchlistItemById(id: String): WatchlistItemLocalDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWatchlistItems(items: List<WatchlistItemLocalDto>)

    // Insert a single item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWatchlistItem(item: WatchlistItemLocalDto)

    // Delete a specific item
    @Query("DELETE FROM watchlist_items WHERE idItem = :id")
    suspend fun deleteWatchlistItemById(id: String)

    // Fetch all details
    @Query("SELECT * FROM watchlist_details")
    suspend fun getAllWatchlistDetails(): List<WatchlistDetailLocalDto>

    // Fetch detail by ID (fix for your issue)
    @Query("SELECT * FROM watchlist_details WHERE idItem = :id")
    suspend fun getWatchlistDetailById(id: String): WatchlistDetailLocalDto? // Add this method

    // Insert a single detail
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWatchlistDetail(detail: WatchlistDetailLocalDto)

    // Update a specific detail
    @Update
    suspend fun updateWatchlistDetail(detail: WatchlistDetailLocalDto)

    // Delete a specific detail
    @Query("DELETE FROM watchlist_details WHERE idItem = :id")
    suspend fun deleteWatchlistDetailById(id: String)
}
