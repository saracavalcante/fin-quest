package br.com.finquest.features.home.ui.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoalsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF7F7F7))
            .padding(horizontal = 36.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 60.dp),
            text = "Seja Bem-vindo",
            fontSize = 24.sp
        )
        Text(
            text = "dom., 12 de janeiro",
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    GoalsScreen()
}