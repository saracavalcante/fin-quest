package br.com.finquest.features.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.core.ui.R

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    text: String,
    actions: @Composable (RowScope.() -> Unit) = {},
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = null,
                    onClick = { onBackClick() }
                ),
                painter = painterResource(R.drawable.ic_arrow_left),
                tint = Color.Black,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = text,
                fontFamily = FontFamily,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            actions()
        }
        HorizontalDivider(color = Color(0xFFF5F5F5))
    }
}

@Preview
@Composable
private fun ComponentPreview() {
    TopAppBar(text = "Nova Meta") {}
}