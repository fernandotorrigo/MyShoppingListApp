package com.ftorrigo.myshoppinglistapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogCustom(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    icon: ImageVector,
    setName: (name: String) -> Unit,
    nameItem: String,
    setQtd: (qtd: String) -> Unit,
    qtdItem: String
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text("Add Shopping Item")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = nameItem,
                    onValueChange = { setName(it) },
                    singleLine = true
                )
                OutlinedTextField(
                    value = qtdItem,
                    onValueChange = { setQtd(it) },
                    singleLine = true
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}