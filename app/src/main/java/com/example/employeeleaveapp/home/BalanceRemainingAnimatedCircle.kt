package com.example.employeeleaveapp.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DonutChart(
    totalLeave: Int,
    usedLeave: Int,
    modifier: Modifier = Modifier
) {
    val remainingLeave = 1 - (usedLeave.toFloat() / totalLeave.toFloat())

    Canvas(
        modifier = modifier.size(200.dp),
        onDraw = {
            val canvasSize = size.width
            val donutRadius = canvasSize / 4
            val strokeWidth = canvasSize / 8
            val center = Offset(canvasSize / 2, canvasSize / 2)

            // Draw the background circle (full donut)
            drawCircle(
                color = Color.LightGray,
                center = center,
                radius = donutRadius,
                style = Stroke(strokeWidth)
            )

            // Draw the used part of the donut
            drawArc(
                color = Color.Magenta,
                startAngle = 0f,
                sweepAngle = 360f * remainingLeave,
                useCenter = false,
                topLeft = Offset(center.x - donutRadius, center.y - donutRadius),
                size = Size(donutRadius * 2, donutRadius * 2),
                style = Stroke(strokeWidth)
            )
        }
    )
}

@Composable
fun LeaveChart() {
    DonutChart(totalLeave = 26, usedLeave = 10)
}

@Preview
@Composable
fun LeaveChartPreview() {
    LeaveChart()
}