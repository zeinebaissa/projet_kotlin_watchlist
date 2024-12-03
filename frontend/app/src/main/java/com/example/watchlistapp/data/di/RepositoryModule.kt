package com.example.watchlistapp.data.di

import com.example.watchlistapp.data.data_sources.local.dao.WatchlistDao
import com.example.watchlistapp.data.data_sources.remote.WatchlistApi
import com.example.watchlistapp.data.repositories.WatchlistRepositoryImpl
import com.example.watchlistapp.domain.repositories.WatchlistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWatchlistRepository(
        dao: WatchlistDao,
        api: WatchlistApi
    ): WatchlistRepository {
        return WatchlistRepositoryImpl(dao, api)
    }
}
