package com.example.codingquiz.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.codingquiz.R

@Composable
fun ExitQuizDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    BaseQuizDialog(
        message = stringResource(R.string.quiz_exit_message),
        onDismissRequest = onDismissRequest,
        onConfirmation = onConfirmation,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewExitQuizDialog() {
    ExitQuizDialog(
        onDismissRequest = {},
        onConfirmation = {},
    )
}
