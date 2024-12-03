package com.example.watchlistapp.ui.watchlist_list.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.models.WatchlistItem
import com.example.watchlistapp.domain.models.toWatchlistDetail
import com.example.watchlistapp.domain.repositories.WatchlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WatchlistListViewModel @Inject constructor(
    private val repository: WatchlistRepository
) : ViewModel() {

    private val _state = mutableStateOf(WatchlistListState())
    val state: State<WatchlistListState> = _state

    init {
        loadWatchlistItems(forceRefresh = true)
    }

    // Add this function to refresh the list
    fun refreshList() {
        loadWatchlistItems(forceRefresh = true)
    }

    fun loadWatchlistItems(forceRefresh: Boolean) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                val items = repository.getAllWatchlistItems(forceRefresh)
                _state.value = WatchlistListState(watchlistItems = items)
            } catch (e: Exception) {
                _state.value = WatchlistListState(
                    error = e.message ?: "Failed to load watchlist items"
                )
            }
        }
    }

    fun addItem(newItem: WatchlistDetail) {
        viewModelScope.launch {
            try {
                repository.addWatchlistItem(newItem)
                refreshList() // Refresh after adding
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Failed to add item: ${e.message}"
                )
            }
        }
    }

    fun updateItem(updatedDetail: WatchlistDetail) {
        viewModelScope.launch {
            try {
                repository.updateWatchlistItem(updatedDetail.idItem, updatedDetail)
                refreshList() // Refresh after updating
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Failed to update item: ${e.message}"
                )
            }
        }
    }

    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            try {
                repository.deleteWatchlistItem(itemId)
                refreshList() // Refresh after deleting
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Failed to delete item: ${e.message}"
                )
            }
        }
    }

    fun toggleItemStatus(itemId: String, isWatched: Boolean) {
        viewModelScope.launch {
            try {
                val newStatusText = if (isWatched) "Watched" else "To watch"
                val currentItem = _state.value.watchlistItems.find { it.idItem == itemId }
                if (currentItem != null) {
                    val updatedDetail = currentItem.toWatchlistDetail(status = newStatusText)
                    repository.updateWatchlistItem(itemId, updatedDetail)
                    refreshList() // Refresh after toggling status
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Failed to update status: ${e.message}"
                )
            }
        }
    }
}
