package br.com.finquest.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.finquest.core.theme.FontFamily

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    placeholder: String = "",
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onClick: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = null,
            onClick = onClick
        ),
        value = value,
        onValueChange = onValueChange,
        shape = shape,
        enabled = enabled,
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = FontFamily,
                fontSize = 12.sp,
                color = Color.Black
            )
        },
        label = label,
        keyboardOptions = keyboardOptions,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFFBCBCBC),
            focusedBorderColor = Color(0xFFBCBCBC),
            disabledBorderColor = Color(0xFFBCBCBC),
            disabledPlaceholderColor = Color.Black,
            disabledTextColor = Color.Black
        )
    )
}