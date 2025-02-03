package br.com.finquest.features.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.finquest.core.common.enums.BottomSheetType
import br.com.finquest.core.components.BaseBottomSheet
import br.com.finquest.core.components.keyboard.KeyboardContent
import br.com.finquest.core.theme.FontFamily

@Composable
fun BalanceBottomSheet(
    targetAmount: String = "",
    savedAmount: String = "",
    type: BottomSheetType,
    setBalance: (String) -> Unit,
    onDelete: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val condition = type == BottomSheetType.BALANCE
    BaseBottomSheet(
        title = if (condition) "Valor da meta" else "Saldo atual",
        onDismissRequest = { onDismissRequest() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BalanceContent(if (condition) targetAmount else savedAmount)
            KeyboardContent(
                onKeyClick = { key ->
                    when (key) {
                        "DEL" -> {
                            val balance = if (condition) targetAmount else savedAmount
                            onDelete(balance)
                        }

                        "CHECK" -> { onDismissRequest() }

                        else -> { setBalance(key) }
                    }
                }
            )
        }
    }
}

@Composable
fun BalanceContent(
    editBalance: String = ""
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "R$",
            fontFamily = FontFamily,
            color = Color(0xFFBCBCBC)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = editBalance.ifEmpty { "00,00" },
            fontFamily = FontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            color = Color.Black,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComponent() {
    BalanceContent()
}