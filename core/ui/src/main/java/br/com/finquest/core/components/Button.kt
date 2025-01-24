package br.com.finquest.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.finquest.core.theme.FontFamily

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    containerColor: Color = Color.Black,
    contentColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontFamily = FontFamily,
            color = textColor
        )
    }
}

@Composable
fun OutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    contentColor: Color = Color.Black,
    borderColor: Color = Color.Black,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontFamily = FontFamily,
            color = contentColor
        )
    }
}