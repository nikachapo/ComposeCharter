package com.chapo.compose_charter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DonutChart(
    chartModels: List<ChartModel>,
    chartSize: Dp,
    modifier: Modifier = Modifier,
    start: Float = 270f,
    backgroundColor: Color = Color(0xFFF0E9E9),
    strokeWidth: Float = 100f,
    elevation: Dp = 30.dp,
    cap: StrokeCap = StrokeCap.Round
) {
    val background = MaterialTheme.colors.background
    Canvas(
        modifier = modifier
            .size(chartSize)
            .shadow(elevation = elevation, shape = CircleShape, clip = false)

    ) {

        drawCircle(background)

        drawCircle(
            color = backgroundColor,
            style = Stroke(strokeWidth, cap = cap),
        )

        var startAngle = start
        chartModels.forEach { chartModel ->
            drawArc(
                chartModel.color,
                startAngle = startAngle,
                sweepAngle = (chartModel.value * 3.6f).also { startAngle += it },
                useCenter = false,
                style = Stroke(strokeWidth, cap = cap),
            )
        }

    }
}