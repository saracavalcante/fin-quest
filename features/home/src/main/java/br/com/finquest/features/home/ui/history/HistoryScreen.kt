package br.com.finquest.features.home.ui.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGoals()
    }

    HistoryScreen(state)
}

@Composable
fun HistoryScreen(
    state: HistoryUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Histórico",
            fontFamily = FontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        FilterContent(state = state)
        Text(
            text = "Janeiro",
            fontFamily = FontFamily
        )
        repeat(5) {
            HistoryItem()
            HorizontalDivider(color = Color(0xFFF5F5F5))
        }
    }
}

@Composable
fun FilterContent(
    state: HistoryUiState,
    onItemClick: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = state.goals.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
                .clickable { expanded = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = "",
                    tint = Color(0xFFBCBCBC)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Filtrar",
                    fontFamily = FontFamily,
                    color = Color(0xFFBCBCBC)
                )
            }
            DropdownMenu(
                expanded = expanded,
                containerColor = Color(0xFFF5F5F5),
                onDismissRequest = { expanded = false }
            ) {
                state.goals.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item.goal.name ?: "",
                                fontFamily = FontFamily
                            )
                        },
                        onClick = onItemClick
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(
                    Color(0xFFF5F5F5),
                    RoundedCornerShape(12.dp)
                )
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_payments),
                contentDescription = "",
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Carro",
                fontFamily = FontFamily,
                fontSize = 12.sp,
                color = Color(0xFFBCBCBC)
            )
            Text(
                text = "+R$ 100,00",
                fontFamily = FontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "21/01/2025",
            fontFamily = FontFamily,
            fontSize = 12.sp,
            color = Color(0xFFBCBCBC)
        )
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    HistoryScreen(state = HistoryUiState())
}