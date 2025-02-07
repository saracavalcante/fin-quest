package br.com.finquest.features.home.ui.details

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import br.com.finquest.core.common.util.toLocalDate
import br.com.finquest.core.common.util.toMoneyString
import br.com.finquest.core.components.BaseDialog
import br.com.finquest.core.components.DefaultButton
import br.com.finquest.core.components.OutlineButton
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R
import br.com.finquest.features.home.ui.components.CircularProgressIndicator
import br.com.finquest.features.home.ui.components.TopAppBar
import java.time.LocalDate
import java.time.Period

@Composable
fun GoalDetailsScreen(
    viewModel: GoalDetailsViewModel,
    onBackClick: () -> Unit,
    onEditClick: (Goal) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGoalById()
    }

    GoalDetailsScreen(
        state = state,
        viewModel = viewModel,
        onBackClick = onBackClick,
        onEditClick = onEditClick
    )
}

@Composable
fun GoalDetailsScreen(
    state: GoalDetailsUiState,
    viewModel: GoalDetailsViewModel,
    onBackClick: () -> Unit,
    onEditClick: (Goal) -> Unit
) {
    val progress = state.goal?.let { goal ->
        (goal.targetAmount?.toFloat()?.let {
            goal.savedAmount?.toFloat()?.div(it)
        })?.times(100)
    } ?: 0f

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBarContent(
                state = state,
                viewModel = viewModel,
                onClick = onBackClick,
                onEditClick = onEditClick
            )
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                text = "Adicionar saldo"
            ) { }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Meta",
                fontFamily = FontFamily,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "R$ ${state.goal?.targetAmount?.toMoneyString()}",
                fontFamily = FontFamily,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(dataUsage = progress)
            Spacer(modifier = Modifier.height(24.dp))
            GoalContent(state = state)
            Spacer(modifier = Modifier.height(36.dp))
            SavingsRecommendation(state = state)
        }
    }

    if (state.showPauseDialog) {
        PauseDialog(
            onPauseClick = {
                viewModel.pauseGoal(true)
                onBackClick()
            },
            onDismissRequest = { viewModel.showPauseDialog(false) }
        )
    }
}

@Composable
fun SavingsRecommendation(state: GoalDetailsUiState) {
    val startDate = LocalDate.now()
    val remainingAmount = state.goal?.targetAmount?.minus(state.goal.savedAmount ?: 0) ?: 0

    val monthsRemaining = if (state.goal?.deadline?.isNotBlank() == true) {
        val period = Period.between(startDate, state.goal.deadline.toLocalDate())
        period.toTotalMonths()
    } else 6

    val suggestedMonthlySavings = if (monthsRemaining > 0) {
        remainingAmount / monthsRemaining
    } else remainingAmount

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Economize:",
                fontFamily = FontFamily,
            )
            Text(
                text = "R$ ${suggestedMonthlySavings.toMoneyString()}",
                fontFamily = FontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "nos próximos $monthsRemaining meses\npara cumprir a meta",
                fontFamily = FontFamily,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun GoalContent(
    state: GoalDetailsUiState
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (state.goal?.deadline?.isNotBlank() == true) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Data Limite",
                    fontFamily = FontFamily
                )
                Text(
                    text = state.goal.deadline ?: "",
                    fontFamily = FontFamily
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Saldo",
                fontFamily = FontFamily
            )
            Text(
                text = "R$ ${state.goal?.savedAmount?.toMoneyString()}",
                fontFamily = FontFamily
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Faltam",
                fontFamily = FontFamily
            )
            Text(
                text = "R$ ${
                    (state.goal?.targetAmount?.minus(state.goal.savedAmount ?: 0))?.coerceAtLeast(
                        0
                    )?.toMoneyString()
                }",
                fontFamily = FontFamily
            )
        }
    }
}

@Composable
private fun TopAppBarContent(
    modifier: Modifier = Modifier,
    state: GoalDetailsUiState,
    viewModel: GoalDetailsViewModel,
    onClick: () -> Unit,
    onEditClick: (Goal) -> Unit
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        text = state.goal?.name ?: "",
        onBackClick = onClick,
        actions = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Icon(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = null,
                        onClick = {
                            state.goal?.let { goal ->
                                onEditClick(goal)
                            }
                        }
                    ),
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = null,
                    tint = Color.Black
                )
                Icon(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = null,
                        onClick = { viewModel.showPauseDialog(true) }
                    ),
                    painter = painterResource(R.drawable.ic_pause),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    )
}

@Composable
fun PauseDialog(
    onPauseClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    BaseDialog(
        onDismissRequest = onDismissRequest
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painterResource(R.drawable.ic_pause_circle),
            contentDescription = null,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Deseja pausar sua meta?",
            fontFamily = FontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Você poderá continuar a partir de qualquer momento.",
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
                text = "Pausar",
                onClick = onPauseClick
            )
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    GoalDetailsScreen(
        viewModel = GoalDetailsViewModel(),
        state = GoalDetailsUiState()
    ) {}
}*/
