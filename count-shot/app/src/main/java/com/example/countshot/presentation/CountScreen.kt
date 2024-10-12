package com.example.countshot.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Alert

@Composable
fun CountScreen(
    countViewModel: CountViewModel = hiltViewModel()
) {
    // TODO: collectAsStateWithLifecycleに変更
    val uiState: CountUiState by countViewModel.countUiState.collectAsState()
    CountContent(
        count = uiState.count,
        onCountUpClicked = { countViewModel.incrementCount() },
        onResetClicked = { countViewModel.resetCount() }
    )
}

@Composable
private fun CountContent(
    count: Int,
    onCountUpClicked: () -> Unit,
    onResetClicked: () -> Unit
) {
    var isResetDialogShown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable { onCountUpClicked() },
            text = count.toString(),
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            modifier = Modifier
                .size(
                    width = 56.dp,
                    height = 28.dp
                )
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            isResetDialogShown = true
                        }
                    )
                },
            onClick = {/* クリックの処理はなし */ },
            shape = RoundedCornerShape(10),
        ) {
            Text(
                text = "RESET",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (isResetDialogShown) {
        ResetAlertDialog(
            onDismiss = { isResetDialogShown = false },
            onConfirm = {
                onResetClicked()
                isResetDialogShown = false
            }
        )
    }
}

@Composable
private fun ResetAlertDialog(
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Alert(
        title = { Text(text = "Reset count?") },
        negativeButton = {
            Button(
                colors = ButtonDefaults.secondaryButtonColors(),
                onClick = {
                    onDismiss()
                }
            ) {
                Text("No")
            }
        },
        positiveButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Yes")
            }
        }
    ) {
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun PreviewCountScreen() {
    CountContent(
        count = 5,
        onCountUpClicked = {},
        onResetClicked = {}
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun PreviewResetAlertDialog() {
    ResetAlertDialog()
}
