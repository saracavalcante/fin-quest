package br.com.finquest.features.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.finquest.core.components.BaseBottomSheet
import br.com.finquest.core.components.DefaultButton
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.features.home.ui.addgoal.AddGoalUiState
import br.com.finquest.features.home.ui.addgoal.AddGoalViewModel
import br.com.finquest.features.home.ui.addgoal.GoalColors
import br.com.finquest.features.home.ui.addgoal.GoalIcons

@Composable
fun CustomizationBottomSheet(
    state: AddGoalUiState,
    viewModel: AddGoalViewModel,
    onDismissRequest: () -> Unit
) {
    BaseBottomSheet(
        title = "Escolha uma imagem",
        onDismissRequest = onDismissRequest
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(GoalIcons.icons) { icon ->
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(Color(0xFFF5F5F5))
                        .border(
                            width = 1.dp,
                            color = if (state.icon == icon) Color.Black else Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable(
                            indication = null,
                            interactionSource = null,
                            onClick = {
                                viewModel.setIcon(icon)
                            }
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(icon),
                        contentDescription = ""
                    )
                }
            }
        }
        Text(
            text = "Escolha uma cor",
            fontFamily = FontFamily,
            fontSize = 16.sp
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(GoalColors.colors) { color ->
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape)
                        .background(color = color)
                        .border(
                            width = 1.dp,
                            color = if (state.color == color) Color.Black else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable(
                            indication = null,
                            interactionSource = null,
                            onClick = {
                                viewModel.setColor(color)
                            }
                        )
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black
                ),
                onClick = { viewModel.cancelCustomization() }
            ) {
                Text(
                    text = "Cancelar",
                    fontFamily = FontFamily
                )
            }
            DefaultButton(
                modifier = Modifier.weight(1f),
                text = "Finalizar",
                onClick = onDismissRequest
            )
        }
    }
}