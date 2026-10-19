package com.example.mood_logger_cesar_sanchez.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material3.ColorScheme
import androidx.wear.compose.material3.MaterialTheme

/**
 * Custom Wear Material 3 color scheme for Mood Logger.
 * Only the colors used by this app's components are defined;
 * the remaining ones keep the library defaults.
 */
private val MoodLoggerColorScheme = ColorScheme(
    primary = CobaltBlue,                // Main button container
    onPrimary = IceBlue,                 // Content displayed on the main button
    surfaceContainer = NightBlue,        // Surface containers (message card)
    onSurface = IceBlue,                 // Content displayed on surfaces
    onSurfaceVariant = MutedIceBlue,     // Secondary text
    outline = HairlineBlue,              // Thin borders
    background = DeepNavyBlue,           // Screen background
    onBackground = IceBlue               // Content displayed on the background
)

/**
 * Applies the Mood Logger blue palette to all the content it wraps.
 */
@Composable
fun Mood_Logger_Cesar_SanchezTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MoodLoggerColorScheme,
        content = content
    )
}