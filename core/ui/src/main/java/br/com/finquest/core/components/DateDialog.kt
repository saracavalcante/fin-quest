package br.com.finquest.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialog(
    modifier: Modifier = Modifier,
    state: DatePickerState,
    onConfirm: (Long) -> Unit,
    onDismissRequest: () -> Unit,
) {
    DatePickerDialog(
        modifier = modifier.fillMaxWidth(),
        colors = DatePickerDefaults.colors(
            containerColor = Color.White
        ),
        confirmButton = {
            ConfirmContent(
                onConfirm = {
                    val selectedDateMillis = state.selectedDateMillis
                    if (selectedDateMillis != null) {
                        onConfirm(selectedDateMillis)
                    }
                },
                onDismissRequest = onDismissRequest
            )
        },
        onDismissRequest = onDismissRequest
    ) {
        DatePicker(
            state = state,
            showModeToggle = false,
            headline = null,
            title = null,
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                selectedDayContainerColor = Color.Black,
                selectedYearContainerColor = Color.Black,
                todayDateBorderColor = Color.Black,
                todayContentColor = Color.Black
            )
        )
    }
}

@Composable
fun ConfirmContent(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlineButton(
            text = "Cancelar",
            onClick = onDismissRequest
        )
        DefaultButton(
            modifier = Modifier.weight(1f),
            text = "Confirmar",
            onClick = onConfirm
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PreviewComponent() {
    val datePickerState = rememberDatePickerState()
    DateDialog(
        state = datePickerState,
        onConfirm = {},
        onDismissRequest = {}
    )
}