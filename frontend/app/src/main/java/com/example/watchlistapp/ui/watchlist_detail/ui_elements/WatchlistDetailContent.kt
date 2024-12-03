package com.example.watchlistapp.ui.watchlist_detail.ui_elements

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.watchlistapp.domain.models.WatchlistDetail

@Composable
fun WatchlistDetailContent(
    detail: WatchlistDetail,
    mode: String,
    onSaveClick: (WatchlistDetail) -> Unit,
    onCancelClick: () -> Unit
) {
    var title by remember { mutableStateOf(detail.title) }
    var type by remember { mutableStateOf(detail.type) }
    var status by remember { mutableStateOf(detail.status) }
    var comment by remember { mutableStateOf(detail.comment ?: "") }

    // Options for type and status
    val typeOptions = listOf("Movie", "Show")
    val statusOptions = listOf("To watch", "Watched")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Watchlist Detail",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        // Title
        if (mode == "edit") {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = "Title: $title",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Type Dropdown
        if (mode == "edit") {
            var expanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Type: $type")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    typeOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                type = option
                                expanded = false
                            }
                        )
                    }
                }
            }
        } else {
            Text(
                text = "Type: $type",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Status Dropdown
        if (mode == "edit") {
            var expanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Status: $status")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    statusOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                status = option
                                expanded = false
                            }
                        )
                    }
                }
            }
        } else {
            Text(
                text = "Status: $status",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Comment
        if (mode == "edit") {
            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                label = { Text("Comment") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = "Comment: $comment",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if (mode == "edit") {
                Button(onClick = {
                    val updatedDetail = detail.copy(
                        title = title,
                        type = type,
                        status = status,
                        comment = comment
                    )
                    onSaveClick(updatedDetail)
                }) {
                    Text("Save")
                }
            }
            TextButton(onClick = onCancelClick) {
                Text("Cancel")
            }
        }
    }
}
