package com.chapo.composecharter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.chapo.compose_charter.*
import com.chapo.composecharter.ui.theme.ComposeCharterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCharterTheme {
                var currentChartType by remember {
                    mutableStateOf(ChartType.Pie)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        ChartTypePicker(onChartTypeChanged = {
                            currentChartType = it
                        })
                    }
                    Box(
                        modifier = Modifier
                            .height(400.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        ChartSamplesScreen {
                            DrawChart(type = currentChartType, list = it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawChart(type: ChartType, list: List<ChartModel>) {
    when (type) {
        ChartType.Area -> {
            AreaChart(
                yAxisValues = list.map { it.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(24.dp),
                lineWidth = 2f,
                lineColors = listOf(
                    MaterialTheme.colors.primary,
                    MaterialTheme.colors.error
                ),
                fillColor = Color.Green
            )
        }
        ChartType.Line -> {
            LineChart(
                yAxisValues = list.map { it.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(24.dp),
                lineWidth = 2f,
                withDots = true,
                lineColors = listOf(
                    MaterialTheme.colors.primary,
                    MaterialTheme.colors.error
                ),
                fillColor = Color.Green
            )
        }
        ChartType.Bar -> {
            BarChart(
                chartModels = list,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                height = 200.dp
            )
        }
        ChartType.Column -> {
            ColumnChart(
                chartModels = list,
                modifier = Modifier.fillMaxWidth(),
                height = 150.dp,
                columnWidth = 150f,
                spaceWidth = 40f,
                cornerRadius = CornerRadius(12f, 12f),
                showValues = true
            )
        }
        ChartType.Donut -> {
            DonutChart(
                chartModels = list,
                chartSize = 150.dp,
                strokeWidth = 100f,
                elevation = 30.dp,
                cap = StrokeCap.Round
            )
        }
        ChartType.Pie -> {
            PieChart(
                chartModels = list,
                chartSize = 150.dp,
                elevation = 30.dp,
                showCenterDot = true
            )
        }
    }
}

enum class ChartType {
    Area,
    Bar,
    Column,
    Line,
    Donut,
    Pie
}