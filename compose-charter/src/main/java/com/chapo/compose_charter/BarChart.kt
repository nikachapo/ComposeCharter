package com.chapo.compose_charter

import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BarChart(
    chartModels: List<ChartModel>,
    modifier: Modifier = Modifier,
    height: Dp = 200.dp,
    columnWidth: Float = 150f,
    spaceWidth: Float = 40f,
    cornerRadius: CornerRadius = CornerRadius(12f, 12f),
    showValues: Boolean = true,
    @ColorInt valueTextColor: Int = Color.BLACK,
    @FontRes textFontRes: Int = -1
) {
    val font = getFont(textFontRes)

    val maxValue = chartModels.maxOf { it.value }

    val textPaint = remember {
        Paint().apply {
            textSize = columnWidth / 3
            color = valueTextColor
            textAlign = Paint.Align.CENTER
            typeface = font
        }
    }

    Canvas(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
    ) {
        var lastY = 0f
        chartModels.forEach { chartModel ->
            drawRoundRect(
                color = chartModel.color,
                topLeft = Offset(
                    x = 0f,
                    y = (lastY + columnWidth + spaceWidth).also { lastY = it }
                ),
                size = Size(chartModel.value * size.width / maxValue, columnWidth),
                cornerRadius = cornerRadius
            )
            if (showValues) {
                drawContext.canvas.nativeCanvas.drawText(
                    (chartModel.text ?: "") + " " + chartModel.value.toInt().toString(),
                    (chartModel.value * size.width / maxValue) / 2,
                    lastY + columnWidth / 2,
                    textPaint
                )
            }
        }
    }
}