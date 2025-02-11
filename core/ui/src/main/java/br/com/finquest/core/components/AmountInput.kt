package br.com.finquest.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.finquest.core.theme.FontFamily

@Composable
fun AmountInput(
    modifier: Modifier = Modifier,
    value: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
            text = value.ifEmpty { "0,00" },
            fontFamily = FontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            color = Color.Black,
            maxLines = 1
        )
    }
}