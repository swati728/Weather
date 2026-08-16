package com.example.weatherapp.presentation.ui



import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.math.roundToInt
import kotlin.random.Random

enum class WeatherEffect { SUNNY, CLOUDY, RAIN, STORM, SNOW, NIGHT_CLEAR }

data class WeatherGradient(val top: Color, val bottom: Color, val effect: WeatherEffect)

fun getWeatherGradient(condition: String, isDay: Boolean = true): WeatherGradient {
    return when {
        condition.contains("Clear", ignoreCase = true) ->
            if (isDay) WeatherGradient(Color(0xFF4A90E2), Color(0xFF8FC6F0), WeatherEffect.SUNNY)
            else WeatherGradient(Color(0xFF0D1B3E), Color(0xFF2C3E6B), WeatherEffect.NIGHT_CLEAR)

        condition.contains("Partly Cloudy", ignoreCase = true) ->
            WeatherGradient(Color(0xFF6E9BD1), Color(0xFFB8D4E8), WeatherEffect.CLOUDY)

        condition.contains("Cloudy", ignoreCase = true) ->
            WeatherGradient(Color(0xFF7A8B99), Color(0xFFB0BEC5), WeatherEffect.CLOUDY)

        condition.contains("Storm", ignoreCase = true) || condition.contains("Thunder", ignoreCase = true) ->
            WeatherGradient(Color(0xFF1A1A2E), Color(0xFF34344A), WeatherEffect.STORM)

        condition.contains("Rain", ignoreCase = true) ->
            WeatherGradient(Color(0xFF3E4C59), Color(0xFF5C6B73), WeatherEffect.RAIN)

        condition.contains("Snow", ignoreCase = true) ->
            WeatherGradient(Color(0xFFB0C4DE), Color(0xFFE8EEF2), WeatherEffect.SNOW)

        else -> WeatherGradient(Color(0xFF6E9BD1), Color(0xFFB8D4E8), WeatherEffect.CLOUDY)
    }
}

/**
 * Full animated background: gradient base + weather-specific particle/light effects,
 * inspired by the layered look of Apple Weather.
 */
@Composable
fun WeatherBackground(
    gradient: WeatherGradient,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        // base gradient
        drawRect(
            brush = Brush.verticalGradient(listOf(gradient.top, gradient.bottom)),
            size = size
        )
    }

    when (gradient.effect) {
        WeatherEffect.SUNNY -> SunEffect()
        WeatherEffect.NIGHT_CLEAR -> Unit // gradient alone reads as night; could add stars later
        WeatherEffect.RAIN -> RainEffect(dropCount = 120, speedMultiplier = 1f)
        WeatherEffect.STORM -> RainEffect(dropCount = 180, speedMultiplier = 1.6f)
        WeatherEffect.SNOW -> SnowEffect()
        WeatherEffect.CLOUDY -> Unit // gradient alone; could add drifting cloud shapes later
    }
}

@Composable
private fun SunEffect() {
    val transition = rememberInfiniteTransition(label = "sun")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(20000, easing = LinearEasing)),
        label = "sunRotation"
    )
    val pulse by transition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Reverse),
        label = "sunPulse"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width * 0.78f, size.height * 0.18f)
        val radius = size.minDimension * 0.12f * pulse

        // glow halo
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFFFF3B0).copy(alpha = 0.5f), Color.Transparent),
                center = center,
                radius = radius * 3.5f
            ),
            radius = radius * 3.5f,
            center = center
        )

        // rotating rays
        rotate(degrees = rotation, pivot = center) {
            repeat(12) { i ->
                val angle = i * 30f
                rotate(degrees = angle, pivot = center) {
                    drawLine(
                        color = Color(0xFFFFF9DB).copy(alpha = 0.4f),
                        start = Offset(center.x, center.y - radius * 1.3f),
                        end = Offset(center.x, center.y - radius * 1.9f),
                        strokeWidth = 6f
                    )
                }
            }
        }

        // sun disc
        drawCircle(color = Color(0xFFFFE58A), radius = radius, center = center)
        drawCircle(color = Color(0xFFFFFFFF).copy(alpha = 0.3f), radius = radius * 0.6f, center = center)
    }
}

private data class RainDrop(
    val startX: Float,
    val length: Float,
    val speed: Float,
    val delay: Float,
    val alpha: Float
)

@Composable
private fun RainEffect(dropCount: Int, speedMultiplier: Float) {
    val drops = remember(dropCount) {
        List(dropCount) {
            RainDrop(
                startX = Random.nextFloat(),
                length = Random.nextFloat() * 14f + 14f,
                speed = (Random.nextFloat() * 0.4f + 0.8f) * speedMultiplier,
                delay = Random.nextFloat(),
                alpha = Random.nextFloat() * 0.3f + 0.25f
            )
        }
    }

    val transition = rememberInfiniteTransition(label = "rain")
    val time by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)),
        label = "rainTime"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        drops.forEach { drop ->
            // each drop loops independently based on its own speed/delay for a natural, non-uniform look
            val progress = ((time * drop.speed + drop.delay) % 1f)
            val x = drop.startX * size.width
            val y = progress * (size.height + drop.length) - drop.length

            drawLine(
                color = Color(0xFFCFE8FF).copy(alpha = drop.alpha),
                start = Offset(x, y),
                end = Offset(x - 4f, y + drop.length),
                strokeWidth = 3f
            )
        }
    }
}

private data class Snowflake(
    val startX: Float,
    val radius: Float,
    val speed: Float,
    val delay: Float,
    val drift: Float
)

@Composable
private fun SnowEffect() {
    val flakes = remember {
        List(80) {
            Snowflake(
                startX = Random.nextFloat(),
                radius = Random.nextFloat() * 4f + 2f,
                speed = Random.nextFloat() * 0.3f + 0.2f,
                delay = Random.nextFloat(),
                drift = Random.nextFloat() * 20f - 10f
            )
        }
    }

    val transition = rememberInfiniteTransition(label = "snow")
    val time by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(4000, easing = LinearEasing)),
        label = "snowTime"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        flakes.forEach { flake ->
            val progress = ((time * flake.speed + flake.delay) % 1f)
            val x = flake.startX * size.width + (flake.drift * kotlin.math.sin(progress * 2 * Math.PI)).toFloat()
            val y = progress * (size.height + flake.radius * 2) - flake.radius

            drawCircle(
                color = Color.White.copy(alpha = 0.8f),
                radius = flake.radius,
                center = Offset(x.roundToInt().toFloat(), y)
            )
        }
    }
}