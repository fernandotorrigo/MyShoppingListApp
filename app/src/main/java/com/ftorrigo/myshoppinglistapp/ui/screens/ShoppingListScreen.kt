package com.ftorrigo.myshoppinglistapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ftorrigo.myshoppinglistapp.model.ShoppingItem
import com.ftorrigo.myshoppinglistapp.ui.components.AlertDialogCustom
import com.ftorrigo.myshoppinglistapp.ui.components.ShoppingItemEditor
import com.ftorrigo.myshoppinglistapp.ui.components.ShoppingListItem

@Composable
fun ShoppingListScreen() {

    var sItems by remember {
        mutableStateOf(listOf<ShoppingItem>())
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var nameItem by remember {
        mutableStateOf("")
    }

    var qtdItem by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) { item ->
                if (item.isEditing) {
                    ShoppingItemEditor(item = item,
                        onEditComplete = { editedName, editedQuantity ->
                            sItems = sItems.map { item.copy(isEditing = false) }
                            val editedItem = sItems.find { it.id == item.id }
                            editedItem?.let {
                                it.name = editedName
                                it.quantity = editedQuantity
                            }

                        })
                } else {
                    ShoppingListItem(
                        item = item,
                        onDeleteClick = { sItems = sItems - item },
                        onEditClick = {
                            sItems = sItems.map { item.copy(isEditing = it.id == item.id) }
                        }
                    )
                }

            }
        }
    }

    if (showDialog) {
        AlertDialogCustom(
            onDismissRequest = { showDialog = false },
            onConfirmation = {
                if (nameItem.isNotBlank()) {
                    val newItem = ShoppingItem(
                        id = sItems.size + 1,
                        name = nameItem,
                        quantity = qtdItem.toInt()
                    )
                    sItems = sItems + newItem
                    showDialog = false
                    nameItem = ""
                    qtdItem = ""
                }
                showDialog = false
            },
            content = {
                Column {
                    OutlinedTextField(
                        value = nameItem,
                        onValueChange = { nameItem = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = qtdItem,
                        onValueChange = { qtdItem = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            },
            icon = Icons.Default.Info
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListScreenPreview() {
    ShoppingListScreen()
}