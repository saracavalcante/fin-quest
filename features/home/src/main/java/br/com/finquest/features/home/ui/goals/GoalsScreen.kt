package br.com.finquest.features.home.ui.goals

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import br.com.finquest.core.common.util.currentDate
import br.com.finquest.core.common.util.toMoneyString
import br.com.finquest.core.components.BaseDialog
import br.com.finquest.core.components.DefaultButton
import br.com.finquest.core.components.OutlineButton
import br.com.finquest.core.components.SwipeToRevealCard
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R

@Composable
fun GoalsScreen(
    viewModel: GoalsViewModel,
    onGoalClick: (Int?) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGoals()
    }

    GoalsScreen(
        state = state,
        viewModel = viewModel,
        onClick = onGoalClick
    )

    if (state.showDeleteDialog) {
        DeleteDialog(
            viewModel = viewModel,
            onDismissRequest = {
                viewModel.showDeleteDialog(
                    id = null,
                    show = false
                )
            }
        )
    }
}

@Composable
private fun GoalsScreen(
    state: GoalsUiState,
    viewModel: GoalsViewModel,
    onClick: (Int?) -> Unit
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
            text = currentDate(),
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
                    ListContent(
                        goal = item,
                        viewModel = viewModel,
                        onClick = { onClick(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun ListContent(
    goal: Goal,
    viewModel: GoalsViewModel,
    onClick: (Int?) -> Unit
) {
    SwipeToRevealCard(
        actions = listOf {
            Icon(
                modifier = Modifier.clickable {
                    goal.id?.let {
                        viewModel.showDeleteDialog(id = it, show = true)
                    }
                },
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = "",
                tint = Color.Black
            )
            if (goal.isPaused) {
                Icon(
                    modifier = Modifier.clickable {
                        viewModel.resumeGoal(goal)
                    },
                    painter = painterResource(R.drawable.ic_pause),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        },
        content = {
            GoalContent(
                goal = goal,
                viewModel = viewModel,
                onClick = { onClick(goal.id) }
            )
        }
    )
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
    viewModel: GoalsViewModel,
    onClick: () -> Unit = {}
) {
    val icon by animateIntAsState(
        targetValue = if (goal.isPinned) R.drawable.ic_keep_filled else R.drawable.ic_keep,
        label = ""
    )

    val color by animateColorAsState(
        targetValue = if (goal.isPaused) Color.Gray else Color.Black,
        label = ""
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        enabled = !goal.isPaused,
        colors = CardDefaults.cardColors(
            containerColor = Color(goal.color ?: 0),
            disabledContainerColor = Color.LightGray
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
                painter = painterResource(goal.icon ?: 0),
                contentDescription = "",
                tint = color
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = goal.name ?: "",
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
                                enabled = !goal.isPaused,
                                onClick = {
                                    viewModel.togglePin(goal)
                                }
                            ),
                        painter = painterResource(icon),
                        contentDescription = "",
                        tint = color
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "R$ ${goal.savedAmount?.toMoneyString()} de R$ ${goal.targetAmount?.toMoneyString()}",
                    fontFamily = FontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                if (!goal.deadline.isNullOrBlank()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Data limite:",
                            fontFamily = FontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = goal.deadline ?: "",
                            fontFamily = FontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                LinearProgressIndicator(
                    progress = {
                        (goal.targetAmount?.toFloat()?.let {
                            goal.savedAmount?.toFloat()?.div(it)
                        })?.coerceIn(0f, 1f) ?: 0f
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = color,
                    trackColor = Color.White,
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

@Composable
fun DeleteDialog(
    viewModel: GoalsViewModel,
    onDismissRequest: () -> Unit
) {
    BaseDialog(
        onDismissRequest = onDismissRequest
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painterResource(R.drawable.ic_warning),
            contentDescription = null,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Tem certeza que deseja excluir?",
            fontFamily = FontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Você não poderá recuperar\na meta depois.",
            fontFamily = FontFamily,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlineButton(
                modifier = Modifier.weight(1f),
                text = "Cancelar",
                onClick = onDismissRequest
            )
            DefaultButton(
                modifier = Modifier.weight(1f),
                text = "Continuar",
                onClick = {
                    viewModel.deleteGoal()
                    viewModel.showDeleteDialog(show = false)
                }
            )
        }
    }
}

/*
@Preview
@Composable
private fun ScreenPreview() {
    GoalsScreen(GoalsUiState(), GoalsViewModel()){}
}*/
