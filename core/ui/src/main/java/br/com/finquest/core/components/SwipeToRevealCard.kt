package br.com.finquest.core.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

const val ANIMATE_DURATION = 500
const val MIN_DRAG_AMOUNT = 6

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SwipeToRevealCard(
    modifier: Modifier = Modifier,
    isRevealed: Boolean,
    actionCount: Int,
    onExpanded: () -> Unit,
    onCollapsed: () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    content: @Composable () -> Unit
) {
    val iconSize = 48.dp
    val iconSpacing = 16.dp
    val totalOffset = (iconSize + iconSpacing) * actionCount
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = rememberTransition(transitionState, "cardTransition")
    val cardBgColor by transition.animateColor(
        label = "",
        transitionSpec = { tween(durationMillis = ANIMATE_DURATION) },
        targetValueByState = {
            if (isRevealed) Color.Red else Color.Green
        }
    )
    val offsetTransition by transition.animateFloat(
        label = "",
        transitionSpec = { tween(durationMillis = ANIMATE_DURATION) },
        targetValueByState = {
            if (isRevealed) -totalOffset.value else 0f
        }
    )

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp),
        ) {
            actions()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offsetTransition.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        when {
                            dragAmount < -MIN_DRAG_AMOUNT -> onExpanded()
                            dragAmount >= MIN_DRAG_AMOUNT -> onCollapsed()
                        }
                    }
                }
        ) {
            content()
        }
    }
}