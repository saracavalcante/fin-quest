package br.com.finquest.core.components.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyboardButton(
    modifier: Modifier = Modifier,
    symbol: String = "",
    icon: Int? = null,
    iconColor: Color = Color.Black,
    color: Color = Color(0xFFF5F5F5),
    width: Dp = 80.dp,
    height: Dp = 80.dp,
    textStyle: TextStyle = TextStyle(),
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(width = width, height = height)
            .background(
                color = color,
                shape = RoundedCornerShape(28.dp)
            )
            .clickable(
                indication = null,
                interactionSource = null,
                onClick = { onClick() }
            )
            .then(modifier)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(icon),
                contentDescription = "",
                tint = iconColor
            )
        } else {
            Text(
                text = symbol,
                style = textStyle,
                fontSize = 36.sp,
                color = Color.Black
            )
        }
    }
}