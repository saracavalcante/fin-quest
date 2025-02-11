package br.com.finquest.features.home.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import br.com.finquest.core.theme.FontFamily
import kotlin.math.min

@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    dataUsage: Float = 60f,
    size: Dp = 200.dp,
    indicatorThickness: Dp = 12.dp,
    animationDuration: Int = 1000
) {
    var dataUsageRemember by remember { mutableFloatStateOf(0f) }
    val dataUsageAnimate = animateFloatAsState(
        targetValue = dataUsageRemember,
        animationSpec = tween(
            durationMillis = animationDuration
        ), label = ""
    )

    LaunchedEffect(dataUsage) {
        dataUsageRemember = min(dataUsage, 100f)
    }

    Box(
        modifier = modifier
            .size(size)
            .padding(top = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(size)
        ) {
            drawArc(
                color = Color(0xFFF5F5F5),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    width = indicatorThickness.toPx(),
                    cap = StrokeCap.Round
                ),
                size = Size(
                    width = (size - indicatorThickness).toPx(),
                    height = (size - indicatorThickness).toPx()
                ),
                topLeft = Offset(
                    x = (indicatorThickness / 2).toPx(),
                    y = (indicatorThickness / 2).toPx()
                )
            )

            val sweepAngle = (dataUsageAnimate.value) * 360 / 100

            drawArc(
                color = Color.Black,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = indicatorThickness.toPx(),
                    cap = StrokeCap.Round
                ),
                size = Size(
                    width = (size - indicatorThickness).toPx(),
                    height = (size - indicatorThickness).toPx()
                ),
                topLeft = Offset(
                    x = (indicatorThickness / 2).toPx(),
                    y = (indicatorThickness / 2).toPx()
                )
            )
        }

        DisplayText(animateNumber = dataUsageAnimate)
    }
}

@Composable
fun DisplayText(
    animateNumber: State<Float>
) {
    Column(
        modifier = Modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = (animateNumber.value).toInt().toString() + "%",
            fontFamily = FontFamily,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}