package br.com.finquest.features.home.ui.addgoal

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.finquest.core.components.CustomOutlinedTextField
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R
import br.com.finquest.core.utils.dashedBorder
import br.com.finquest.features.home.ui.components.CustomizationBottomSheet
import br.com.finquest.features.home.ui.components.TopAppBar
import kotlinx.coroutines.launch

@Composable
fun AddGoalScreen(viewModel: AddGoalViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AddGoalScreen(state, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGoalScreen(
    state: AddGoalUiState,
    viewModel: AddGoalViewModel
) {
    var isChecked by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                text = "Nova Meta",
                onBackClick = {}
            )
            ImageContent(viewModel = viewModel)
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
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.balance,
                onValueChange = { viewModel.setBalance(it) },
                placeholder = "Valor da Meta",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.currentBalance,
                onValueChange = { viewModel.setCurrentBalance(it) },
                placeholder = "Saldo que você já tem",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Possui data limite?",
                    fontFamily = FontFamily,
                    fontSize = 12.sp
                )
                Checkbox(
                    checked = isChecked,
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = Color(0xFFBCBCBC),
                        checkedColor = Color.Black
                    ),
                    onCheckedChange = {
                        isChecked = !isChecked
                    }
                )
            }
            AnimatedVisibility(
                visible = isChecked
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                    headline = null,
                    title = null,
                    colors = DatePickerDefaults.colors(
                        containerColor = Color.White,
                        selectedDayContainerColor = Color.Black,
                        selectedYearContainerColor = Color.Black,
                        todayDateBorderColor = Color.Black
                    )
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {}
            ) {
                Text(
                    text = "Criar meta",
                    fontFamily = FontFamily
                )
            }
        }

        if (state.openCustomization) {
            CustomizationBottomSheet(
                onDismissRequest = {
                    viewModel.openCustomization(false)
                }
            )
        }
    }
}

@Composable
fun ImageContent(
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
            .background(color = Color.White)
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
                painter = painterResource(R.drawable.ic_savings),
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
}

@Preview
@Composable
private fun PreviewScreen() {
    AddGoalScreen(
        AddGoalUiState(),
        AddGoalViewModel()
    )
}