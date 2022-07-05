package com.chapo.compose_charter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
    chartModels: List<ChartModel>,
    chartSize: Dp,
    modifier: Modifier = Modifier,
    start: Float = 270f,
    backgroundColor: Color = Color(0xFFF0E9E9),
    elevation: Dp = 15.dp,
    showCenterDot: Boolean = false,
    centerDotColor: Color = Color.Black
) {

    Canvas(
        modifier = modifier
            .size(chartSize)
            .shadow(elevation = elevation, shape = CircleShape)
    ) {

        drawCircle(backgroundColor)

        var startAngle = start
        chartModels.forEach { chartModel ->
            drawArc(
                brush = Brush.verticalGradient(
                    listOf(
                        chartModel.color,
                        chartModel.color.copy(alpha = 0.3f)
                    )
                ),
                startAngle = startAngle,
                sweepAngle = (chartModel.value * 3.6f).also { startAngle += it },
                useCenter = true
            )
        }
        if (showCenterDot) {
            drawCircle(radius = 15f, color = centerDotColor.copy(alpha = 0.7f))
        }
    }
}
