package com.example.watchlistapp.data.data_sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.watchlistapp.data.data_sources.local.dao.WatchlistDao
import com.example.watchlistapp.data.data_sources.local.dto.WatchlistDetailLocalDto
import com.example.watchlistapp.data.data_sources.local.dto.WatchlistItemLocalDto

@Database(
    entities = [WatchlistItemLocalDto::class, WatchlistDetailLocalDto::class],
    version = 1,
    exportSchema = false
)
abstract class WatchlistLocalDB : RoomDatabase() {
    abstract fun watchlistDao(): WatchlistDao
}
