package br.com.finquest.features.home.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finquest.core.common.util.toFormattedDate
import br.com.finquest.core.components.CustomOutlinedTextField
import br.com.finquest.core.components.DateDialog
import br.com.finquest.core.components.DefaultButton
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.ui.R
import br.com.finquest.features.home.ui.components.BalanceBottomSheet
import br.com.finquest.features.home.ui.components.TopAppBar

@Composable
fun EditGoalScreen(
    goal: Goal,
    viewModel: EditGoalViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setGoal(goal)
    }

    EditGoalScreen(
        goal = goal,
        viewModel = viewModel,
        state = state,
        onClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditGoalScreen(
    goal: Goal,
    state: EditGoalUiState,
    viewModel: EditGoalViewModel,
    onClick: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Editar Meta",
                onBackClick = onClick
            )
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                text = "Editar",
                onClick = {
                    if (state.balance.isEmpty()) {
                        viewModel.setAmountError()
                    } else {
                        viewModel.updateGoal(goal = goal)
                        onClick()
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color(goal.color ?: 0))
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(goal.icon ?: 0),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.name,
                onValueChange = { viewModel.setName(it) },
                label = "Nome da meta",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.balance,
                placeholder = "R$ 0,00",
                label = "Valor da meta",
                error = if (state.setAmountError) "Campo obrigatório" else null,
                onValueChange = { viewModel.setBalance(it) },
                onClick = {
                    viewModel.openBottomSheet(open = true)
                },
                enabled = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.deadline,
                enabled = false,
                label = "Data limite",
                placeholder = "dd/mm/aaaa",
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = ""
                    )
                },
                onValueChange = { viewModel.setDeadline(it) },
                onClick = {
                    viewModel.openDateDialog(true)
                }
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                text = "Não obrigatorio",
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light

            )
        }
    }

    if (state.openAddBalance) {
        BalanceBottomSheet(
            targetAmount = state.balance,
            type = state.bottomSheetType,
            setBalance = { viewModel.setBalance(it) },
            onDelete = { viewModel.delete(it) },
            onDismissRequest = { viewModel.openBottomSheet(open = false) }
        )
    }

    if (state.openDateDialog) {
        DateDialog(
            state = datePickerState,
            onConfirm = { dateMillis ->
                viewModel.setDeadline(dateMillis.toFormattedDate())
                viewModel.openDateDialog(false)
            },
            onDismissRequest = {
                viewModel.openDateDialog(false)
            }
        )
    }
}

/*
@Preview
@Composable
private fun PreviewScreen() {
    EditGoalScreen(
        viewModel = EditGoalViewModel(),
        state = EditGoalUiState(),
        goal = Goal(
            name = "Teste",
            targetAmount = 1000000,
            color = 0,
            icon = 0,
            deadline = null,
            savedAmount = 0,
            status = ""
        ),
        onClick = {}
    )
}*/
