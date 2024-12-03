package com.example.watchlistapp.domain.use_cases

import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.repositories.WatchlistRepository

class GetWatchlistDetailByIdUseCase(
    private val repository: WatchlistRepository
) {
    suspend operator fun invoke(id: String, forceRefresh: Boolean): WatchlistDetail? {
        return repository.getWatchlistDetailById(id, forceRefresh)
    }
}
