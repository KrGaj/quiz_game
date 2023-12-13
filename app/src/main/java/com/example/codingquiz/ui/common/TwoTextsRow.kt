package com.example.codingquiz.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TwoTextsRow(
    leftText: String,
    rightText: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .height(intrinsicSize = IntrinsicSize.Max)
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = leftText,
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = rightText,
                    textAlign = TextAlign.End,
                )
            }
        }
    }
}
