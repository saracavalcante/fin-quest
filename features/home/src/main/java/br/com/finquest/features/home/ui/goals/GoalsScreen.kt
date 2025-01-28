package br.com.finquest.features.home.ui.goals

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finquest.core.common.enums.GoalEnum
import br.com.finquest.core.common.util.toMoneyString
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R

@Composable
fun GoalsScreen(
    viewModel: GoalsViewModel,
    onGoalClick: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    GoalsScreen(
        state = state,
        viewModel = viewModel,
        onClick = onGoalClick
    )
}

@Composable
private fun GoalsScreen(
    state: GoalsUiState,
    viewModel: GoalsViewModel,
    onClick: (String) -> Unit
) {
    val filteredGoals = state.goals.filter { it.status == state.filter.value }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 60.dp),
            text = "Seja Bem-vindo",
            fontFamily = FontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "dom., 12 de janeiro",
            fontFamily = FontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        FilterContent(state = state, viewModel = viewModel)

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (filteredGoals.isEmpty()) {
                item {
                    EmptyState()
                }
            } else {
                items(state.goals.filter { it.status == state.filter.value }) { item ->
                    GoalContent(goal = item, onClick = { onClick(item.name) })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun FilterContent(
    state: GoalsUiState,
    viewModel: GoalsViewModel
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(GoalEnum.entries.toTypedArray()) { item ->
            FilterChip(
                selected = state.filter.value == item.value,
                onClick = { viewModel.setStatus(item) },
                label = {
                    Text(
                        text = item.value,
                        fontFamily = FontFamily
                    )
                },
                border = BorderStroke(width = 0.dp, color = Color.Transparent),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFFF5F5F5),
                    labelColor = Color.Black,
                    selectedContainerColor = Color.Black,
                    selectedLabelColor = Color.White
                )
            )
        }
    }
}

@Composable
fun GoalContent(
    goal: Goal,
    onClick: () -> Unit = {}
) {
    var keepIcon by remember { mutableIntStateOf(R.drawable.ic_keep) }
    val currentProgress by remember { mutableFloatStateOf(0.3f) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(goal.color)
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(goal.icon),
                contentDescription = "",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = goal.name,
                        fontFamily = FontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier
                            .clickable(
                                interactionSource = null,
                                indication = null,
                                onClick = {
                                    keepIcon = if (keepIcon == R.drawable.ic_keep) {
                                        R.drawable.ic_keep_filled
                                    } else {
                                        R.drawable.ic_keep
                                    }
                                }
                            ),
                        painter = painterResource(keepIcon),
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "R$ ${goal.savedAmount.toMoneyString()} de R$ ${goal.targetAmount.toMoneyString()}",
                    fontFamily = FontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LinearProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = Color.Black,
                    trackColor = Color(0xFFF5F5F5),
                    drawStopIndicator = {}
                )
            }
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_empty),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Nenhuma meta cadastrada!",
            fontFamily = FontFamily,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Comece a economizar para\nrealizar seus sonhos.",
            fontFamily = FontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

/*
@Preview
@Composable
private fun ScreenPreview() {
    GoalsScreen(GoalsUiState(), GoalsViewModel()){}
}*/
