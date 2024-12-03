package com.example.watchlistapp.domain.use_cases

import com.example.watchlistapp.domain.repositories.WatchlistRepository

class DeleteWatchlistItemUseCase(
    private val repository: WatchlistRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deleteWatchlistItem(id)
    }
}
