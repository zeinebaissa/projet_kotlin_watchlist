package com.example.watchlistapp.domain.models

data class WatchlistDetail(
    val idItem: String,
    val title: String,
    val type: String,
    val status: String,
    val comment: String? = null // `comment` est facultatif
)
