package com.example.watchlistapp.domain.use_cases

data class WatchlistUseCases(
    val getAllWatchlistItems: GetAllWatchlistItemsUseCase,
    val getWatchlistDetailById: GetWatchlistDetailByIdUseCase, // Add this line
    val addWatchlistItem: AddWatchlistItemUseCase,
    val updateWatchlistItem: UpdateWatchlistItemUseCase,
    val deleteWatchlistItem: DeleteWatchlistItemUseCase
)
