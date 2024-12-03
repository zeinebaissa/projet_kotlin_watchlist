package com.example.watchlistapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.watchlistapp.data.data_sources.local.WatchlistLocalDB
import com.example.watchlistapp.data.data_sources.local.dao.WatchlistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext context: Context): WatchlistLocalDB {
        return Room.databaseBuilder(
            context,
            WatchlistLocalDB::class.java,
            "watchlist_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWatchlistDao(database: WatchlistLocalDB): WatchlistDao {
        return database.watchlistDao()
    }
}
