package com.example.mood_logger_cesar_sanchez.presentation

import android.app.Activity
import android.app.RemoteInput
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.EdgeButton
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import androidx.wear.input.RemoteInputIntentHelper
import com.example.mood_logger_cesar_sanchez.presentation.theme.CalmBlue
import com.example.mood_logger_cesar_sanchez.presentation.theme.EnergeticBlue
import com.example.mood_logger_cesar_sanchez.presentation.theme.HappyBlue
import com.example.mood_logger_cesar_sanchez.presentation.theme.IceBlue
import com.example.mood_logger_cesar_sanchez.presentation.theme.Mood_Logger_Cesar_SanchezTheme
import com.example.mood_logger_cesar_sanchez.presentation.theme.SadBlue
import com.example.mood_logger_cesar_sanchez.presentation.theme.StressedBlue
import com.example.mood_logger_cesar_sanchez.presentation.theme.TiredBlue

// ---------- Step 4: Mood choices and fixed input values ----------
private const val KEY_MOOD = "mood_key"               // Key used to read the result from the Bundle
private const val INPUT_LABEL = "How do you feel?"    // Label shown on the full-screen selection
private const val PROMPT_MESSAGE = "Tap the button to log your mood."

private const val MOOD_STRESSED = "Stressed"
private const val MOOD_ENERGETIC = "Energetic"
private const val MOOD_CALM = "Calm"
private const val MOOD_HAPPY = "Happy"
private const val MOOD_TIRED = "Tired"
private const val MOOD_SAD = "Sad"

// Options offered to the user, in display order
private val MOOD_CHOICES = arrayOf(
    MOOD_STRESSED,
    MOOD_ENERGETIC,
    MOOD_CALM,
    MOOD_HAPPY,
    MOOD_TIRED,
    MOOD_SAD
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoodLoggerApp()
        }
    }
}

// ---------- Step 5: RemoteInput configuration ----------

/** Builds the RemoteInput that shows the six moods and rejects free-form text. */
private fun buildMoodRemoteInput(): RemoteInput =
    RemoteInput.Builder(KEY_MOOD)
        .setLabel(INPUT_LABEL)
        .setChoices(MOOD_CHOICES)
        .setAllowFreeFormInput(false) // Only the listed moods can be selected
        .build()

/** Creates the Intent that opens the full-screen selection. */
private fun createMoodInputIntent(): Intent {
    val intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    RemoteInputIntentHelper.putRemoteInputsExtra(intent, listOf(buildMoodRemoteInput()))
    return intent
}

// ---------- Steps 6 and 7: State, launcher, and main screen ----------

@Composable
fun MoodLoggerApp() {
    // State saved across activity recreation: message text and its color (ARGB integer)
    var moodMessage by rememberSaveable { mutableStateOf("") }
    var moodColor by rememberSaveable { mutableIntStateOf(IceBlue.toArgb()) }

    // Receives the result of the full-screen selection
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Closing the selection without choosing does not return RESULT_OK
        if (result.resultCode == Activity.RESULT_OK) {
            // Read the chosen option from the Bundle using the defined key
            val selectedMood = result.data
                ?.let { RemoteInput.getResultsFromIntent(it) }
                ?.getCharSequence(KEY_MOOD)
                ?.toString()

            if (selectedMood != null) {
                // Conditional logic: each mood has its own message and blue tone
                val (message, color) = when (selectedMood) {
                    MOOD_STRESSED ->
                        "Mood Logged: Stressed. Remember to take a deep breath." to StressedBlue
                    MOOD_ENERGETIC ->
                        "Mood Logged: Energetic! Go for a short walk." to EnergeticBlue
                    MOOD_CALM ->
                        "Mood Logged: Calm. Enjoy this peaceful moment." to CalmBlue
                    MOOD_HAPPY ->
                        "Mood Logged: Happy! Share your smile with someone today." to HappyBlue
                    MOOD_TIRED ->
                        "Mood Logged: Tired. Take a short break and drink some water." to TiredBlue
                    MOOD_SAD ->
                        "Mood Logged: Sad. It's okay to feel this way. Talk to someone you trust." to SadBlue
                    else ->
                        "Mood Logged: $selectedMood." to IceBlue // Fallback; should not occur
                }
                moodMessage = message
                moodColor = color.toArgb()
            }
        }
    }

    Mood_Logger_Cesar_SanchezTheme {
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val transformationSpec = rememberTransformationSpec()
            val isLogged = moodMessage.isNotEmpty()

            ScreenScaffold(
                scrollState = listState,
                // Primary action pinned to the bottom edge; it only opens the selection
                edgeButton = {
                    EdgeButton(onClick = { launcher.launch(createMoodInputIntent()) }) {
                        Text(text = "Log mood")
                    }
                }
            ) { contentPadding ->
                TransformingLazyColumn(contentPadding = contentPadding, state = listState) {
                    item {
                        ListHeader(
                            modifier = Modifier
                                .fillMaxWidth()
                                .transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec)
                        ) {
                            Text(text = "Mood Logger")
                        }
                    }
                    item {
                        // Shows the invitation until a mood is logged
                        MoodMessageCard(
                            message = moodMessage.ifEmpty { PROMPT_MESSAGE },
                            accentColor = if (isLogged) Color(moodColor)
                            else MaterialTheme.colorScheme.outline,
                            textColor = if (isLogged) Color(moodColor)
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

/**
 * Non-interactive surface that presents the message.
 * A slim bar and the text take the tone of the logged mood.
 */
@Composable
private fun MoodMessageCard(
    message: String,
    accentColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(20.dp)

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainer, shape)
            .border(1.dp, MaterialTheme.colorScheme.outline, shape)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 24.dp, height = 3.dp)
                .background(accentColor, RoundedCornerShape(50))
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun DefaultPreview() {
    MoodLoggerApp()
}