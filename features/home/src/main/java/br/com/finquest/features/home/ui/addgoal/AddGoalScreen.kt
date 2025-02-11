package br.com.finquest.features.home.ui.addgoal

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finquest.core.common.enums.BottomSheetType
import br.com.finquest.core.common.util.toFormattedDate
import br.com.finquest.core.components.CustomOutlinedTextField
import br.com.finquest.core.components.DateDialog
import br.com.finquest.core.components.DefaultButton
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R
import br.com.finquest.core.utils.dashedBorder
import br.com.finquest.features.home.ui.components.BalanceBottomSheet
import br.com.finquest.features.home.ui.components.CustomizationBottomSheet

@Composable
fun AddGoalScreen(
    viewModel: AddGoalViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AddGoalScreen(
        state = state,
        viewModel = viewModel,
        navigateToHome = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGoalScreen(
    state: AddGoalUiState,
    viewModel: AddGoalViewModel,
    navigateToHome: () -> Unit = {}
) {
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Nova Meta",
            fontFamily = FontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(26.dp))
        ImageContent(state = state, viewModel = viewModel)
        GoalContent(state = state, viewModel = viewModel)
        Spacer(modifier = Modifier.height(26.dp))
        DateContent(
            onClick = {
                viewModel.openDateDialog(true)
            }
        )
        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Criar meta",
            enabled = state.isEnabled(),
            onClick = {
                viewModel.addGoal()
                navigateToHome()
            }
        )
    }

    if (state.openCustomization) {
        CustomizationBottomSheet(
            state = state,
            viewModel = viewModel,
            onDismissRequest = {
                viewModel.openCustomization(false)
            }
        )
    }

    if (state.openAddBalance) {
        BalanceBottomSheet(
            savedAmount = state.currentBalance,
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

@Composable
fun DateContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Possui data limite?",
            fontFamily = FontFamily,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .background(
                    shape = CircleShape,
                    color = Color(0xFFF5F5F5)
                )
        ) {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = ""
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(26.dp))
}

@Composable
fun ImageContent(
    state: AddGoalUiState,
    modifier: Modifier = Modifier,
    viewModel: AddGoalViewModel
) {
    val chooseIconText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Customize ")
        }
        append("sua meta")
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .dashedBorder(
                color = Color(0xFFBCBCBC),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(color = state.color)
            .padding(vertical = 24.dp)
            .clickable(
                indication = null,
                interactionSource = null,
                onClick = {
                    viewModel.openCustomization(true)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(56.dp),
                painter = painterResource(state.icon),
                contentDescription = "",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = chooseIconText,
                fontFamily = FontFamily,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
    Spacer(modifier = Modifier.height(26.dp))
}

@Composable
fun GoalContent(
    modifier: Modifier = Modifier,
    state: AddGoalUiState,
    viewModel: AddGoalViewModel
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Dados da meta",
            fontFamily = FontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name,
            onValueChange = { viewModel.setName(it) },
            placeholder = "Nome",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            )
        )
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.balance,
            placeholder = "Valor da Meta",
            onValueChange = { viewModel.setBalance(it) },
            onClick = {
                viewModel.openBottomSheet(
                    type = BottomSheetType.BALANCE,
                    open = true
                )
            },
            enabled = false
        )
        CustomOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.currentBalance,
            placeholder = "Saldo que você já tem",
            onValueChange = { viewModel.setCurrentBalance(it) },
            onClick = {
                viewModel.openBottomSheet(
                    type = BottomSheetType.CURRENT_BALANCE,
                    open = true
                )
            },
            enabled = false
        )
    }
}