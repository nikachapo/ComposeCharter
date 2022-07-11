package com.chapo.compose_charter

import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import android.graphics.Path as AndroidPath

@Composable
fun AreaChart(
    yAxisValues: List<Float>,
    modifier: Modifier = Modifier,
    lineWidth: Float = 4f,
    spacing: Float = 100f,
    lineColors: List<Color> = listOf(
        MaterialTheme.colors.primary,
        MaterialTheme.colors.primary
    ),
    fillColor: Color? = null
) {

    val upperValue = remember(yAxisValues) {
        (yAxisValues.maxOfOrNull { it }?.plus(1))?.roundToInt() ?: 0
    }
    val lowerValue = remember(yAxisValues) {
        yAxisValues.minOfOrNull { it }?.toInt() ?: 0
    }

    Canvas(
        modifier = modifier
    ) {
        val space = (size.width - spacing) / yAxisValues.size
        var lastX = 0f
        val strokePath = Path().apply {
            val height = size.height
            for (i in yAxisValues.indices) {
                val yAxisValue = yAxisValues[i]
                val nextYAxisValue = yAxisValues.getOrNull(i + 1) ?: yAxisValues.last()
                val leftRatio = (yAxisValue - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextYAxisValue - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * space
                val y1 = height - spacing - (leftRatio * height)
                val x2 = spacing + (i + 1) * space
                val y2 = height - spacing - (rightRatio * height)
                if (i == 0) {
                    moveTo(x1, y1)
                }
                lastX = (x1 + x2) / 2f
                quadraticBezierTo(
                    x1, y1, lastX, (y1 + y2) / 2f
                )
            }
        }
        if (fillColor != null) {
            val fillPath = AndroidPath(strokePath.asAndroidPath())
                .asComposePath()
                .apply {
                    lineTo(lastX, size.height - spacing)
                    lineTo(spacing, size.height - spacing)
                    close()
                }
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        fillColor.copy(0.5f),
                        Color.Transparent
                    ),
                    endY = size.height - spacing
                )
            )
        }
        drawPath(
            path = strokePath,
            brush = Brush.verticalGradient(lineColors),
            style = Stroke(
                width = lineWidth.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}