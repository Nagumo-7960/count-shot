package com.example.countshot.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

@Composable
fun CountScreen(
    countViewModel: CountViewModel = hiltViewModel()
) {
    // TODO: collectAsStateWithLifecycleに変更
    val uiState: CountUiState by countViewModel.countUiState.collectAsState()
    CountContent(
        count = uiState.count,
        onClicked = { countViewModel.incrementCount() }
    )
}

@Composable
private fun CountContent(
    count: Int,
    onClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClicked() },
            text = count.toString(),
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            modifier = Modifier.size(
                width = 56.dp,
                height = 28.dp
            ),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10),
        ) {
            Text(
                text = "RESET",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
private fun PreviewCountScreen() {
    CountContent(
        count = 5,
        onClicked = {}
    )
}
