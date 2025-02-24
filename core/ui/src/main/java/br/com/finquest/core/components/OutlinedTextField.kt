package br.com.finquest.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.finquest.core.theme.FontFamily

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String? = null,
    placeholder: String = "",
    error: String? = null,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        label?.let { label ->
            Text(
                text = label,
                fontFamily = FontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        OutlinedTextField(
            modifier = modifier.clickable(
                indication = null,
                interactionSource = null,
                onClick = onClick
            ),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontFamily = FontFamily,
                fontSize = 14.sp
            ),
            shape = shape,
            enabled = enabled,
            placeholder = {
                Text(
                    text = placeholder,
                    fontFamily = FontFamily,
                    fontSize = 14.sp,
                    color = colorScheme.onSurfaceVariant
                )
            },
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFBCBCBC),
                focusedBorderColor = Color(0xFFBCBCBC),
                disabledBorderColor = Color(0xFFBCBCBC),
                disabledPlaceholderColor = Color.Black,
                disabledTextColor = colorScheme.onSurfaceVariant
            )
        )
        error?.let { error ->
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = error,
                fontFamily = FontFamily,
                fontSize = 10.sp,
                color = Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComponent() {
    CustomOutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        placeholder = "Testando",
        onValueChange = {},
    )
}