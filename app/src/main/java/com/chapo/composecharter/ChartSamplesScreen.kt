package com.chapo.composecharter

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chapo.compose_charter.ChartModel
import com.chapo.composecharter.components.ChartValueTextField
import kotlinx.coroutines.delay

@Composable
fun ChartSamplesScreen(
    chart: @Composable (List<ChartModel>) -> Unit
) {
    var cyanPercentage by remember { mutableStateOf<Float?>(0f) }
    var greenPercentage by remember { mutableStateOf<Float?>(0f) }
    var yellowPercentage by remember { mutableStateOf<Float?>(0f) }

    LaunchedEffect(key1 = Unit) {
        delay(1000)
        cyanPercentage = 30f
        greenPercentage = 20f
        yellowPercentage = 40f
    }

    val cyanAnimatedPercentageState = animateFloatAsState(
        targetValue = cyanPercentage ?: 0f,
        animationSpec = tween(1000)
    )

    val greenAnimatedPercentageState = animateFloatAsState(
        targetValue = greenPercentage ?: 0f,
        animationSpec = tween(1000)
    )

    val yellowAnimatedPercentageState = animateFloatAsState(
        targetValue = yellowPercentage ?: 0f,
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier.padding(bottom = 24.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        chart(
            listOf(
                ChartModel(cyanAnimatedPercentageState.value, Color.Cyan),
                ChartModel(greenAnimatedPercentageState.value, Color.Green),
                ChartModel(yellowAnimatedPercentageState.value, Color.Yellow),
            )
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ChartValueTextField(color = Color.Cyan, value = cyanPercentage) { value ->
                cyanPercentage = value
            }
            ChartValueTextField(color = Color.Green, value = greenPercentage) { value ->
                greenPercentage = value
            }
            ChartValueTextField(Color.Yellow, yellowPercentage) { value ->
                yellowPercentage = value
            }
        }

    }

}