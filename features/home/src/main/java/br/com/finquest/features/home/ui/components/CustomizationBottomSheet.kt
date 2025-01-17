package br.com.finquest.features.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R
import br.com.finquest.features.home.ui.addgoal.GoalColors
import br.com.finquest.features.home.ui.addgoal.GoalIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizationBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = Color.White,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            BottomSheetDefaults.DragHandle(color = Color(0xFFF5F5F5))
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Escolha uma imagem",
                    fontFamily = FontFamily,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = null,
                        onClick = onDismissRequest
                    ),
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = ""
                )
            }
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(GoalIcons.icons) { icon ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFF5F5F5),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable(
                                indication = null,
                                interactionSource = null,
                                onClick = {}
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
            LazyRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(GoalColors.colors) { color ->
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = color,
                                shape = CircleShape
                            )
                            .clickable(
                                indication = null,
                                interactionSource = null,
                                onClick = {}
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
                    onClick = onDismissRequest
                ) {
                    Text(
                        text = "Cancelar",
                        fontFamily = FontFamily
                    )
                }
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = {}
                ) {
                    Text(
                        text = "Finalizar",
                        fontFamily = FontFamily
                    )
                }
            }
        }
    }
}