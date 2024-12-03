package com.example.watchlistapp.ui.watchlist_list.ui_elements

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.watchlistapp.domain.models.WatchlistDetail
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info

@Composable
fun WatchlistListItem(
    item: WatchlistDetail,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    onStatusToggle: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // RadioButton for Status
        RadioButton(
            selected = item.status == "Watched",
            onClick = { onStatusToggle(item.status != "Watched") }
        )

        // Title in the center
        Text(
            text = item.title,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )

        // Info Icon
        IconButton(onClick = onClick) { // For viewing details
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info"
            )
        }

        // Edit Icon
        IconButton(onClick = onEditClick) { // For editing
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit"
            )
        }

        // Delete Icon
        IconButton(onClick = onDeleteClick) { // For deletion
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}
