package br.com.finquest.features.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.finquest.core.model.data.MonthlyContribution
import br.com.finquest.core.theme.FontFamily
import br.com.finquest.features.home.ui.details.getCompleteMonthlyData

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    values: List<MonthlyContribution>
) {
    val maxAmount = values.maxOfOrNull { it.amount } ?: 1f
    val completeData = getCompleteMonthlyData(values)

    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        completeData.forEach { item ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    if (item.amount > 0) {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(200.dp * item.amount / maxAmount)
                                .background(
                                   color = Color.Black,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = item.month,
                    fontFamily = FontFamily,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComponentPreview() {
    val contributions = listOf(
        MonthlyContribution("Aug", 50f),
        MonthlyContribution("Nov", 1200F),
        MonthlyContribution("Dez", 20F),
    )
    BarChart(values = contributions)
}