package com.example.watchlistapp.domain.use_cases

import com.example.watchlistapp.common.Resource
import com.example.watchlistapp.domain.models.WatchlistItem
import com.example.watchlistapp.domain.repositories.WatchlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllWatchlistItemsUseCase @Inject constructor(
    private val repository: WatchlistRepository
) {
    operator fun invoke(forceRefresh: Boolean): Flow<Resource<List<WatchlistItem>>> = flow {
        try {
            emit(Resource.Loading()) // Émet un état de chargement
            val items = repository.getAllWatchlistItems(forceRefresh) // Récupère les données
            emit(Resource.Success(items)) // Émet un état de succès avec les données
        } catch (e: IOException) {
            emit(Resource.Error("Network error: ${e.message}")) // Erreur réseau
        } catch (e: Exception) {
            emit(Resource.Error("Unexpected error: ${e.message}")) // Autres erreurs
        }
    }
}
