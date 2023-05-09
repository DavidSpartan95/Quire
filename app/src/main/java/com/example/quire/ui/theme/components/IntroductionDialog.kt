package com.example.quire.ui.theme.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun IntroductionDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Welcome to Quire!") },
        text = {
            Text("Looking for an app to get your notes in order? We got you! In Quire app you will be able to make your own custom notes. You can save your notes in folders and mark your favorites with a heart. You can customize your notes to your liking with different fonts and sizes.")
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Got it!")
            }
        }
    )
}
