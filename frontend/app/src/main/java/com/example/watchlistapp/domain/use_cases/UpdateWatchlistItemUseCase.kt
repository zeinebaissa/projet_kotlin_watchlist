package com.example.watchlistapp.domain.use_cases

import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.repositories.WatchlistRepository

class UpdateWatchlistItemUseCase(
    private val repository: WatchlistRepository
) {
    suspend operator fun invoke(id: String, item: WatchlistDetail) {
        repository.updateWatchlistItem(id, item)
    }
}
