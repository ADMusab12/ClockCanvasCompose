package com.codetech.clockcompose.presentation
import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OwlDigitalClock() {
    var currentTime by remember { mutableStateOf(LocalDateTime.now()) }

    // Update time every second
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalDateTime.now()
            delay(1000)
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Magenta,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val timeText = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    val dateText = currentTime.format(DateTimeFormatter.ofPattern("EEE, MMM dd yyyy"))

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Planet-like background
        Canvas(modifier = Modifier.size(340.dp, 200.dp)) {
            val cornerRadius = CornerRadius(40f, 40f)

            // Base black rounded rectangle with subtle texture (vertical gradient)
            drawRoundRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF121212),   // very dark gray - top
                        Color(0xFF000000)    // pure black - bottom
                    )
                ),
                cornerRadius = cornerRadius,
                style = Fill
            )

            // Inner bevel highlight (top-left)
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.12f),
                        Color.Transparent
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(size.width / 2, size.height / 2)
                ),
                cornerRadius = cornerRadius,
                style = Fill
            )

            // Inner shadow (bottom-right)
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.8f)
                    ),
                    start = Offset(size.width / 2, size.height / 2),
                    end = Offset(size.width, size.height)
                ),
                cornerRadius = cornerRadius,
                style = Fill
            )

            // Outer soft glow (ambient shadow) around edges
            drawRoundRect(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Black.copy(alpha = 0.9f), Color.Transparent),
                    center = Offset(size.width * 0.8f, size.height * 0.85f),
                    radius = size.maxDimension / 1.4f
                ),
                cornerRadius = cornerRadius,
                style = Stroke(width = 16f)
            )

            // Glossy glassy reflection overlay (top-left corner)
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White.copy(alpha = 0.14f), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width / 1.8f, size.height / 1.8f)
                ),
                cornerRadius = cornerRadius,
                style = Fill
            )

            // Main Stroke border to finish off
            drawRoundRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF3A3A3A), Color(0xFF1A1A1A))
                ),
                cornerRadius = cornerRadius,
                style = Stroke(width = 6f)
            )

            // Now draw time and date text on top (same as you already have)
            drawContext.canvas.nativeCanvas.apply {
                val timePaint = android.graphics.Paint().apply {
                    color = animatedColor.toArgb()
                    textSize = 56.sp.toPx()
                    typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
                    setShadowLayer(18f, 0f, 0f, animatedColor.copy(alpha = 0.5f).toArgb())
                    textAlign = android.graphics.Paint.Align.CENTER
                }

                val datePaint = android.graphics.Paint().apply {
                    color = Color.LightGray.toArgb()
                    textSize = 22.sp.toPx()
                    typeface = Typeface.DEFAULT_BOLD
                    textAlign = android.graphics.Paint.Align.CENTER
                }

                drawText(
                    timeText,
                    size.width / 2f,
                    size.height / 2f,
                    timePaint
                )

                drawText(
                    dateText,
                    size.width / 2,
                    size.height - 24.dp.toPx(),
                    datePaint
                )
            }
        }
    }
}
