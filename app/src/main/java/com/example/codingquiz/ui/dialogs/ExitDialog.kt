package com.example.codingquiz.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ExitDialog(
    message: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card {
            Column(
                modifier = Modifier.padding(12.dp),
            ) {
                Text(text = message)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = stringResource(id = android.R.string.cancel))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onConfirmation) {
                        Text(text = stringResource(id = android.R.string.ok))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBaseQuizDialog() {
    ExitDialog(
        message = "Test message",
        onDismissRequest = {},
        onConfirmation = {},
    )
}
