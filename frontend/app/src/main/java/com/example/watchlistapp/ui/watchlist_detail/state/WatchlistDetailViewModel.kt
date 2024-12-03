package com.example.watchlistapp.ui.watchlist_detail.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.repositories.WatchlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistDetailViewModel @Inject constructor(
    private val repository: WatchlistRepository
) : ViewModel() {

    private val _state = mutableStateOf(WatchlistDetailState())
    val state: State<WatchlistDetailState> = _state

    fun loadWatchlistDetail(itemId: String) {
        _state.value = WatchlistDetailState(isLoading = true)
        viewModelScope.launch {
            try {
                val detail = repository.getWatchlistDetailById(itemId, forceRefresh = true)
                _state.value = WatchlistDetailState(detail = detail)
            } catch (e: Exception) {
                _state.value = WatchlistDetailState(
                    error = e.message ?: "Failed to load item details"
                )
            }
        }
    }

    fun saveWatchlistDetail(updatedDetail: WatchlistDetail) {
        viewModelScope.launch {
            try {
                repository.updateWatchlistItem(updatedDetail.idItem, updatedDetail)
                _state.value = _state.value.copy(detail = updatedDetail)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Failed to save item: ${e.message}"
                )
            }
        }
    }
}
