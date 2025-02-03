package br.com.finquest.core.components.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.finquest.core.ui.R
import br.com.finquest.core.utils.BalanceFormatter

@Composable
fun KeyboardContent(
    onKeyClick: (String) -> Unit,
) {
    val buttonSpacing = 4.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            KeyboardButton(symbol = "1", onClick = { onKeyClick("1") })
            KeyboardButton(symbol = "2", onClick = { onKeyClick("2") })
            KeyboardButton(symbol = "3", onClick = { onKeyClick("3") })
            KeyboardButton(
                icon = R.drawable.ic_backspace,
                color = Color(0xFFFFE0D6),
                onClick = { onKeyClick("DEL") }
            )
        }

        Spacer(modifier = Modifier.height(buttonSpacing))

        Row(
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(buttonSpacing),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Second Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    KeyboardButton(symbol = "4", onClick = { onKeyClick("4") })
                    KeyboardButton(symbol = "5", onClick = { onKeyClick("5") })
                    KeyboardButton(symbol = "6", onClick = { onKeyClick("6") })
                }

                //Third Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    KeyboardButton(symbol = "7", onClick = { onKeyClick("7") })
                    KeyboardButton(symbol = "8", onClick = { onKeyClick("8") })
                    KeyboardButton(symbol = "9", onClick = { onKeyClick("9") })
                }

                //Fourth Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
                ) {
                    KeyboardButton(symbol = "$", onClick = { onKeyClick("$") })
                    KeyboardButton(symbol = "0", onClick = { onKeyClick("0") })
                    KeyboardButton(symbol = ",", onClick = { onKeyClick(",") })
                }
            }
            KeyboardButton(
                icon = R.drawable.ic_check,
                color = Color.Black,
                iconColor = Color.White,
                height = (80.dp * 3) + (buttonSpacing * 2),
                onClick = { onKeyClick("CHECK") }
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun KeyboardContentPreview() {
    KeyboardContent() {}
}*/
