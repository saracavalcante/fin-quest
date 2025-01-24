package br.com.finquest.features.home.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finquest.core.components.BaseDialog
import br.com.finquest.core.components.DefaultButton
import br.com.finquest.core.components.OutlineButton
import br.com.finquest.core.model.data.MonthlyContribution
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R
import br.com.finquest.features.home.ui.components.BarChart
import br.com.finquest.features.home.ui.components.CircularProgressIndicator
import br.com.finquest.features.home.ui.components.TopAppBar

@Composable
fun GoalDetailsScreen(
    viewModel: GoalDetailsViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    GoalDetailsScreen(
        state = state,
        viewModel = viewModel,
        onBackClick = onBackClick
    )

    if (state.showDeleteDialog) {
        DeleteDialog {
            viewModel.showDeleteDialog(false)
        }

    }

    if (state.showPauseDialog) {
        PauseDialog {
            viewModel.showPauseDialog(false)
        }
    }
}

@Composable
fun GoalDetailsScreen(
    state: GoalDetailsUiState,
    viewModel: GoalDetailsViewModel,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBarContent(viewModel = viewModel, onClick = onBackClick)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Meta",
            fontFamily = FontFamily,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "R$ 1.000,00",
            fontFamily = FontFamily,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(24.dp))
        GoalContent()
        Spacer(modifier = Modifier.height(36.dp))
        ChartContent()
    }
}


@Composable
fun ChartContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        val contributions = listOf(
            MonthlyContribution("Aug", 50f),
            MonthlyContribution("Nov", 1200F),
            MonthlyContribution("Dez", 20F),
        )
        BarChart(values = contributions)
    }
}

@Composable
private fun GoalContent() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Data Limite",
                fontFamily = FontFamily
            )
            Text(
                text = "21/01/2025",
                fontFamily = FontFamily
            )
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
                text = "R$ 10,00",
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
                text = "R$ 980,00",
                fontFamily = FontFamily
            )
        }
    }
}

@Composable
private fun TopAppBarContent(
    modifier: Modifier = Modifier,
    viewModel: GoalDetailsViewModel,
    onClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        text = "Viagem",
        onBackClick = onClick,
        actions = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Icon(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = null,
                        onClick = { }
                    ),
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = null,
                    tint = Color.Black
                )
                Icon(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = null,
                        onClick = { viewModel.showDeleteDialog(true) }
                    ),
                    painter = painterResource(R.drawable.ic_delete),
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
fun DeleteDialog(
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
                onClick = {}
            )
        }
    }
}

@Composable
fun PauseDialog(
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
                onClick = {}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    GoalDetailsScreen(
        viewModel = GoalDetailsViewModel(),
        state = GoalDetailsUiState()
    ) {}
}