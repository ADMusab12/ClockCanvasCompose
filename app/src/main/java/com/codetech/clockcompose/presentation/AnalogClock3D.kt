package com.codetech.clockcompose.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.util.Calendar
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnalogClock3D(
    modifier: Modifier = Modifier,
    size: Dp = 200.dp
) {
    var currentTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    // Update time every second
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime = System.currentTimeMillis()
        }
    }

    val calendar = Calendar.getInstance().apply {
        timeInMillis = currentTime
    }

    val hours = calendar.get(Calendar.HOUR)
    val minutes = calendar.get(Calendar.MINUTE)
    val seconds = calendar.get(Calendar.SECOND)

    androidx.compose.foundation.Canvas(
        modifier = modifier.size(size)
    ) {
        val canvasSize = size.toPx()
        val center = Offset(canvasSize / 2, canvasSize / 2)
        val radius = canvasSize * 0.4f
        val frameThickness = canvasSize * 0.08f

        // Draw 3D frame effect
        // Bottom shadow
        drawRoundRect(
            color = Color(0xFFD4920A),
            topLeft = Offset(frameThickness * 0.3f, frameThickness * 0.3f),
            size = Size(canvasSize - frameThickness * 0.6f, canvasSize - frameThickness * 0.6f),
            cornerRadius = CornerRadius(canvasSize * 0.15f)
        )

        // Main frame (golden/orange)
        drawRoundRect(
            color = Color(0xFFF4A623),
            topLeft = Offset(0f, 0f),
            size = Size(canvasSize, canvasSize),
            cornerRadius = CornerRadius(canvasSize * 0.15f)
        )

        // Inner frame highlight
        drawRoundRect(
            color = Color(0xFFFFC555),
            topLeft = Offset(frameThickness * 0.2f, frameThickness * 0.2f),
            size = Size(canvasSize - frameThickness * 0.4f, canvasSize - frameThickness * 0.4f),
            cornerRadius = CornerRadius(canvasSize * 0.13f)
        )

        // Clock face (white with slight gradient)
        val faceRadius = radius + frameThickness * 0.3f
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.White,
                    Color(0xFFF8F8F8),
                    Color(0xFFE8E8E8)
                ),
                radius = faceRadius
            ),
            center = center,
            radius = faceRadius
        )

        // Inner white face
        drawCircle(
            color = Color.White,
            center = center,
            radius = radius
        )

        // Hour markers (thick lines)
        for (i in 0 until 12) {
            val angle = (i * 30 - 90) * PI / 180
            val startRadius = radius * 0.85f
            val endRadius = radius * 0.95f

            val startX = center.x + cos(angle).toFloat() * startRadius
            val startY = center.y + sin(angle).toFloat() * startRadius
            val endX = center.x + cos(angle).toFloat() * endRadius
            val endY = center.y + sin(angle).toFloat() * endRadius

            drawLine(
                color = Color.Black,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = canvasSize * 0.01f,
                cap = StrokeCap.Round
            )
        }

        // Minute markers (thin lines)
        for (i in 0 until 60) {
            if (i % 5 != 0) { // Skip hour markers
                val angle = (i * 6 - 90) * PI / 180
                val startRadius = radius * 0.90f
                val endRadius = radius * 0.95f

                val startX = center.x + cos(angle).toFloat() * startRadius
                val startY = center.y + sin(angle).toFloat() * startRadius
                val endX = center.x + cos(angle).toFloat() * endRadius
                val endY = center.y + sin(angle).toFloat() * endRadius

                drawLine(
                    color = Color.Black.copy(alpha = 0.6f),
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = canvasSize * 0.003f,
                    cap = StrokeCap.Round
                )
            }
        }

        // Hour hand
        val hourAngle = ((hours % 12) * 30 + minutes * 0.5 - 90) * PI / 180
        val hourHandLength = radius * 0.5f
        val hourEndX = center.x + cos(hourAngle).toFloat() * hourHandLength
        val hourEndY = center.y + sin(hourAngle).toFloat() * hourHandLength

        drawLine(
            color = Color.Black,
            start = center,
            end = Offset(hourEndX, hourEndY),
            strokeWidth = canvasSize * 0.012f,
            cap = StrokeCap.Round
        )

        // Minute hand
        val minuteAngle = (minutes * 6 - 90) * PI / 180
        val minuteHandLength = radius * 0.7f
        val minuteEndX = center.x + cos(minuteAngle).toFloat() * minuteHandLength
        val minuteEndY = center.y + sin(minuteAngle).toFloat() * minuteHandLength

        drawLine(
            color = Color.Black,
            start = center,
            end = Offset(minuteEndX, minuteEndY),
            strokeWidth = canvasSize * 0.008f,
            cap = StrokeCap.Round
        )

        // Second hand
        val secondAngle = (seconds * 6 - 90) * PI / 180
        val secondHandLength = radius * 0.8f
        val secondEndX = center.x + cos(secondAngle).toFloat() * secondHandLength
        val secondEndY = center.y + sin(secondAngle).toFloat() * secondHandLength

        drawLine(
            color = Color.Red,
            start = center,
            end = Offset(secondEndX, secondEndY),
            strokeWidth = canvasSize * 0.004f,
            cap = StrokeCap.Round
        )

        // Center dot
        drawCircle(
            color = Color.Black,
            center = center,
            radius = canvasSize * 0.02f
        )

        // Center highlight
        drawCircle(
            color = Color.White.copy(alpha = 0.7f),
            center = center,
            radius = canvasSize * 0.01f
        )
    }
}