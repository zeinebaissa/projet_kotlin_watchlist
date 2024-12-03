package com.example.watchlistapp.domain.use_cases

import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.repositories.WatchlistRepository

class AddWatchlistItemUseCase(
    private val repository: WatchlistRepository
) {
    suspend operator fun invoke(item: WatchlistDetail) {
        repository.addWatchlistItem(item)
    }
}
