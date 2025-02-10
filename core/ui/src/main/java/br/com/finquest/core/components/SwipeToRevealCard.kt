package br.com.finquest.core.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SwipeToRevealCard(
    modifier: Modifier = Modifier,
    actions: List<@Composable () -> Unit>,
    content: @Composable () -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val maxOffset = -80f * actions.size
    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    val newOffsetX = offsetX + dragAmount
                    offsetX = newOffsetX.coerceIn(maxOffset, 0f)
                }
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            actions.forEach { icon ->
                icon()
            }
        }

        Surface(
            modifier = Modifier
                .offset(x = animatedOffsetX.dp)
                .fillMaxWidth()
        ) {
            content()
        }
    }
}