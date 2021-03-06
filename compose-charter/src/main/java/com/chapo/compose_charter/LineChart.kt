package com.chapo.compose_charter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import android.graphics.Path as AndroidPath

@Composable
fun LineChart(
    yAxisValues: List<Float>,
    modifier: Modifier = Modifier,
    lineWidth: Float = 5f,
    spacing: Float = -1f,
    withDots: Boolean = false,
    dotRadius: Float = 5f,
    lineColors: List<Color> = listOf(
        MaterialTheme.colors.primary,
        MaterialTheme.colors.primary
    ),
    fillColor: Color? = null
) {

    Box(
        modifier = modifier,
        contentAlignment = Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {

            val max = yAxisValues.maxOf { it }

            var lastX = 0f
            val path = Path()
            val spaceLength = if (spacing != -1f) {
                spacing
            } else {
                val space = size.width / yAxisValues.size
                space + space / yAxisValues.size
            }
            yAxisValues.forEachIndexed { i, point ->

                val x = when (i) {
                    0 -> 0f
                    yAxisValues.lastIndex -> size.width
                    else -> lastX + spaceLength
                }
                val y = -(size.height * point / max) + size.height

                if (i == 0) {
                    path.moveTo(0f, y)
                }

                path.lineTo(x, y)

                lastX = x

                if (withDots) {
                    drawCircle(
                        brush = Brush.linearGradient(lineColors),
                        radius = dotRadius.dp.toPx(),
                        center = Offset(x, y)
                    )
                }
            }

            drawPath(
                path = path,
                brush = Brush.linearGradient(lineColors),
                style = Stroke(width = lineWidth.dp.toPx())
            )

            if (fillColor != null) {
                val fillPath = AndroidPath(path.asAndroidPath())
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
                            fillColor.copy(alpha = 0.5f),
                            Color.Transparent
                        ),
                        endY = size.height - spacing
                    )
                )
            }
        }
    }
}