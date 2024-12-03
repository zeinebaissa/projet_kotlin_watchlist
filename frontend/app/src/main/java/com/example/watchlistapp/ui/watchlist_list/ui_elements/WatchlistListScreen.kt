package com.example.watchlistapp.ui.watchlist_list.ui_elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.watchlistapp.domain.models.WatchlistDetail
import com.example.watchlistapp.domain.models.toWatchlistDetail
import com.example.watchlistapp.ui.watchlist_list.state.WatchlistListViewModel
@Composable
fun WatchlistListScreen(
    viewModel: WatchlistListViewModel,
    onItemClick: (String, String) -> Unit, // Handles navigation to details (info or edit)
    onDeleteClick: (String) -> Unit, // Handles delete action
    onStatusToggle: (String, Boolean) -> Unit // Handles status toggle
) {
    val state = viewModel.state.value
    val showForm = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val itemToDelete = remember { mutableStateOf<String?>(null) }

    // Form Fields for New Item
    val newItemTitle = remember { mutableStateOf("") }
    val newItemType = remember { mutableStateOf("Movie") }
    val newItemStatus = remember { mutableStateOf("To watch") }
    val newItemComment = remember { mutableStateOf("") }

    // Function to reset form fields
    fun resetFormFields() {
        newItemTitle.value = ""
        newItemType.value = "Movie"
        newItemStatus.value = "To watch"
        newItemComment.value = ""
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Text(
                    text = "Error: ${state.error}",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
            else -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Add Item Button
                    Button(
                        onClick = {
                            if (!showForm.value) {
                                resetFormFields() // Reset form fields when opening the form
                            }
                            showForm.value = !showForm.value
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Item",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = if (showForm.value) "Cancel" else "Add Item")
                    }

                    // Show Add Form
                    if (showForm.value) {
                        Card(
                            elevation = CardDefaults.cardElevation(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = "Add New Item",
                                    style = MaterialTheme.typography.titleLarge
                                )

                                // Title Input
                                OutlinedTextField(
                                    value = newItemTitle.value,
                                    onValueChange = { newItemTitle.value = it },
                                    label = { Text("Title") },
                                    modifier = Modifier.fillMaxWidth()
                                )

                                // Type Dropdown
                                var typeExpanded by remember { mutableStateOf(false) }
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    OutlinedButton(
                                        onClick = { typeExpanded = true },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(text = "Type: ${newItemType.value}")
                                    }
                                    DropdownMenu(
                                        expanded = typeExpanded,
                                        onDismissRequest = { typeExpanded = false }
                                    ) {
                                        listOf("Movie", "Show").forEach { type ->
                                            DropdownMenuItem(
                                                text = { Text(text = type) },
                                                onClick = {
                                                    newItemType.value = type
                                                    typeExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }

                                // Status Dropdown
                                var statusExpanded by remember { mutableStateOf(false) }
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    OutlinedButton(
                                        onClick = { statusExpanded = true },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(text = "Status: ${newItemStatus.value}")
                                    }
                                    DropdownMenu(
                                        expanded = statusExpanded,
                                        onDismissRequest = { statusExpanded = false }
                                    ) {
                                        listOf("To watch", "Watched").forEach { status ->
                                            DropdownMenuItem(
                                                text = { Text(text = status) },
                                                onClick = {
                                                    newItemStatus.value = status
                                                    statusExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }

                                // Comment Input
                                OutlinedTextField(
                                    value = newItemComment.value,
                                    onValueChange = { newItemComment.value = it },
                                    label = { Text("Comment") },
                                    modifier = Modifier.fillMaxWidth()
                                )

                                // Save and Cancel Buttons
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    TextButton(onClick = { showForm.value = false }) {
                                        Text("Cancel")
                                    }
                                    Button(
                                        onClick = {
                                            if (newItemTitle.value.isNotBlank()) {
                                                val newItem = WatchlistDetail(
                                                    idItem = "", // Backend will generate
                                                    title = newItemTitle.value,
                                                    type = newItemType.value,
                                                    status = newItemStatus.value,
                                                    comment = newItemComment.value
                                                )
                                                viewModel.addItem(newItem)
                                                showForm.value = false
                                            }
                                        }
                                    ) {
                                        Text("Save")
                                    }
                                }
                            }
                        }
                    }

                    // List of Items (unchanged)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        items(state.watchlistItems.map { it.toWatchlistDetail() }) { item ->
                            WatchlistListItem(
                                item = item,
                                onClick = { onItemClick(item.idItem, "info") }, // View details
                                onEditClick = { onItemClick(item.idItem, "edit") }, // Edit item
                                onDeleteClick = {
                                    itemToDelete.value = item.idItem
                                    showDialog.value = true
                                }, // Delete item
                                onStatusToggle = { newStatus ->
                                    onStatusToggle(item.idItem, newStatus)
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }

        // Confirmation Dialog (unchanged)
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Delete Confirmation") },
                text = { Text("Are you sure you want to delete this item?") },
                confirmButton = {
                    Button(
                        onClick = {
                            itemToDelete.value?.let { id ->
                                onDeleteClick(id)
                            }
                            showDialog.value = false
                        }
                    ) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
