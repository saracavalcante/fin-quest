package br.com.finquest.features.home.ui.addamount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finquest.core.common.util.toMoneyString
import br.com.finquest.core.components.AmountInput
import br.com.finquest.core.components.keyboard.KeyboardContent
import br.com.finquest.core.model.data.Goal
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.features.home.ui.components.TopAppBar

@Composable
fun AddAmountScreen(
    goal: Goal?,
    viewModel: AddAmountViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AddAmountScreen(
        state = state,
        viewModel = viewModel,
        goal = goal,
        onBackClick = onBackClick
    )
}

@Composable
private fun AddAmountScreen(
    state: AddAmountUiState,
    viewModel: AddAmountViewModel,
    goal: Goal?,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "Adicionar saldo",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = goal?.name ?: "",
                fontFamily = FontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "${goal?.targetAmount?.toMoneyString()}",
                fontFamily = FontFamily,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(36.dp))
            AmountInput(
                value = state.balance
            )
            Spacer(modifier = Modifier.height(24.dp))
            KeyboardContent(
                onKeyClick = { key ->
                    when (key) {
                        "DEL" -> {
                            viewModel.delete(state.balance)
                        }

                        "CHECK" -> {
                            viewModel.updateGoal(goal)
                            onBackClick()
                        }

                        else -> {
                            viewModel.setBalance(key)
                        }
                    }
                }
            )
        }
    }
}

/*
@Preview
@Composable
private fun PreviewScreen() {
    AddAmountScreen()
}*/
