package com.example.codingquiz.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.codingquiz.R

@Composable
fun ExitAppDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    BaseQuizDialog(
        message = stringResource(id = R.string.app_exit_message),
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExitAppDialog() {
    ExitAppDialog(
        onDismissRequest = {},
        onConfirmation = {},
    )
}
