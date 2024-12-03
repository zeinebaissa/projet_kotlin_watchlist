package com.example.watchlistapp.domain.models

data class WatchlistItem(
    val idItem: String,
    val title: String,
    val status: String
)
fun WatchlistItem.toWatchlistDetail(
    currentDetail: WatchlistDetail? = null, // Détails existants passés ici
    status: String = this.status // Priorité à la mise à jour du statut
): WatchlistDetail {
    return WatchlistDetail(
        idItem = this.idItem,
        title = this.title,
        status = status, // Mise à jour du statut
        type = currentDetail?.type ?: "", // Si `currentDetail` est null, utilise une valeur par défaut
        comment = currentDetail?.comment ?: "" // Même logique pour le commentaire
    )
}
