package com.chapo.compose_charter

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.min

@Composable
fun LineChart(
    yAxisValues: List<Float>,
    modifier: Modifier = Modifier,
    lineWidth: Float = 4f,
    shouldAnimate: Boolean = true,
    shouldDrawDots: Boolean = false,
    dotRadius: Float = 10f,
    lineColors: List<Color> = listOf(
        MaterialTheme.colors.primary,
        MaterialTheme.colors.primary
    )
) {
    val x = remember { Animatable(0f) }
    val xTarget = (yAxisValues.size - 1).toFloat()
    LaunchedEffect(shouldAnimate) {
        x.animateTo(
            targetValue = xTarget,
            animationSpec = tween(
                durationMillis = if (shouldAnimate) 500 else 0,
            ),
        )
    }

    Canvas(modifier = modifier) {
        val path = Path()
        val xBounds = Pair(0f, xTarget)
        val yBounds = getBounds(yAxisValues)
        val scaleX = size.width / (xBounds.second - xBounds.first)
        val scaleY = size.height / (yBounds.second - yBounds.first)
        val yMove = yBounds.first * scaleY
        val interval = (0..min(yAxisValues.size - 1, x.value.toInt()))
        interval.forEach { value ->
            val xPoint = value * scaleX
            val yPoint = size.height - (yAxisValues[value] * scaleY) + yMove
            if (value == 0) {
                path.moveTo(0f, yPoint)
                return@forEach
            }
            path.lineTo(xPoint, yPoint)
            if (shouldDrawDots) {
                drawCircle(
                    brush = Brush.linearGradient(lineColors),
                    radius = dotRadius.dp.toPx(),
                    center = Offset(xPoint, yPoint)
                )
            }
        }

        drawPath(
            path = path,
            brush = Brush.linearGradient(lineColors),
            style = Stroke(width = lineWidth.dp.toPx())
        )
    }
}

private fun getBounds(list: List<Float>): Pair<Float, Float> {
    var min = Float.MAX_VALUE
    var max = -Float.MAX_VALUE
    list.forEach {
        min = min.coerceAtMost(it)
        max = max.coerceAtLeast(it)
    }
    return Pair(min, max)
}