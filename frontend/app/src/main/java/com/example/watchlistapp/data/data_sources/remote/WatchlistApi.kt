package com.example.watchlistapp.data.data_sources.remote

import com.example.watchlistapp.data.data_sources.remote.dto.WatchlistDetailDto
import com.example.watchlistapp.data.data_sources.remote.dto.WatchlistDto
import retrofit2.http.*
import com.example.watchlistapp.common.Constants

interface WatchlistApi {

    @GET("watchlist")
    suspend fun getAllWatchlistItems(): List<WatchlistDto>

    @GET("watchlist/{id}") // Utilisation directe du chemin statique
    suspend fun getWatchlistItemById(@Path(Constants.PARAM_ITEM_ID) id: String): WatchlistDetailDto

    @POST("watchlist")
    suspend fun addWatchlistItem(@Body item: WatchlistDetailDto)

    @PUT("watchlist/{id}") // Utilisation directe du chemin statique
    suspend fun updateWatchlistItem(
        @Path(Constants.PARAM_ITEM_ID) id: String,
        @Body item: WatchlistDetailDto
    )

    @DELETE("watchlist/{id}") // Utilisation directe du chemin statique
    suspend fun deleteWatchlistItem(@Path(Constants.PARAM_ITEM_ID) id: String)
}
