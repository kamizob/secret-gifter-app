package com.example.secretgifter2.ui.components

import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Wheel(
    names: List<String>,
    targetName: String?,
    onFinished: () -> Unit
) {
    if (names.isEmpty()) return
    var rotation by remember { mutableStateOf(0f) }
    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(durationMillis = 3000),
        finishedListener = {
            onFinished()
        }
    )
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Canvas(
                    modifier = Modifier.size(300.dp)
                ) {
                    val anglePerItem = 360f / names.size
                    val colors = List(names.size) { i ->
                        Color.hsl(
                            hue = (i * (360f / names.size)),
                            saturation = 0.7f,
                            lightness = 0.6f
                        )
                    }
                    names.forEachIndexed { index, name ->
                        drawArc(
                            color = colors[index % colors.size],
                            startAngle = index * anglePerItem + animatedRotation - 90f,
                            sweepAngle = anglePerItem,
                            useCenter = true
                        )
                        val angle = (index * anglePerItem + anglePerItem / 2 + animatedRotation - 90f)
                        val radians = Math.toRadians(angle.toDouble())

                        val radius = size.minDimension / 2.5f

                        val x = center.x + (radius * cos(radians)).toFloat()
                        val y = center.y + (radius * sin(radians)).toFloat()

                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                name,
                                x,
                                y,
                                Paint().apply {
                                    textAlign = Paint.Align.CENTER
                                    textSize = 30f
                                    color = android.graphics.Color.BLACK
                                }
                            )
                        }
                    }
                }
                Canvas(
                    modifier = Modifier
                        .size(30.dp)
                        .offset(y = (-20).dp)

                ) {
                    val path = Path().apply {
                        moveTo(0f, 0f)
                        lineTo(size.width, 0f)
                        lineTo(size.width / 2, size.height)
                        close()
                    }

                    drawPath(
                        path = path,
                        color = Color.Yellow
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (names.isEmpty() || targetName == null) return@Button

                val targetIndex = names.indexOf(targetName)
                if (targetIndex == -1) return@Button
                val anglePerItem = 360f / names.size
                val targetAngle = 360f - (targetIndex * anglePerItem + anglePerItem / 2)
                val currentRotation = rotation % 360f
                val delta = (targetAngle - currentRotation + 360f) % 360f
                rotation += 360f * 5 + delta
            }) {
                Text("Spin!!!")
            }

        }

    }



}