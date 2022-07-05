package com.chapo.composecharter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ChartValueTextField(color: Color, value: Float?, onValueChange: (Float?) -> Unit) {
    BasicTextField(
        modifier = Modifier
            .width(88.dp)
            .height(48.dp)
            .wrapContentSize(Alignment.Center)
            .clip(RoundedCornerShape(16.dp))
            .background(color)
            .padding(8.dp),
        value = value?.toInt()?.toString() ?: "",
        onValueChange = {
            if (it.isNotEmpty()) onValueChange(it.toFloat()) else onValueChange(null)
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
    )
}