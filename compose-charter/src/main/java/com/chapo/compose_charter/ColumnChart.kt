package com.chapo.compose_charter

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat

@Composable
fun ColumnChart(
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

    val valueTextPaint = remember {
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
        var lastX = 0f
        chartModels.forEach { chartModel ->
            drawRoundRect(
                color = chartModel.color,
                topLeft = Offset(
                    x = (lastX + columnWidth + spaceWidth).also { lastX = it },
                    y = size.height
                ),
                size = Size(columnWidth, -chartModel.value * size.height / maxValue),
                cornerRadius = cornerRadius
            )
            if (showValues) {
                drawContext.canvas.nativeCanvas.drawText(
                    chartModel.value.toInt().toString(),
                    lastX + columnWidth / 2,
                    size.height - (chartModel.value * size.height / maxValue) / 2,
                    valueTextPaint
                )
            }
        }
    }
}

@Composable
fun getFont(textFontRes: Int?): Typeface? {
    if (textFontRes == -1) return null
    val font = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalContext.current.resources.getFont(textFontRes!!)
    } else {
        ResourcesCompat.getFont(LocalContext.current, textFontRes!!)
    }
    return font
}
