# Mood Logger – Wear OS

> Standalone Wear OS app built with Kotlin and Jetpack Compose. The user logs their mood from the watch by choosing one of six predefined options (RemoteInput with `setChoices()`), and the app shows a unique message and a blue tone for each mood.
---

## ℹ️ Information & Prerequisites

Before you start, make sure you have the following installed on your machine:

| Tool           | Required Version                  | Download                                   |
|----------------|-----------------------------------|--------------------------------------------|
| Android Studio | Recent stable version             | https://developer.android.com/studio       |
| JDK            | Included with Android Studio      | (no separate install needed)               |
| Wear OS SDK    | Wear OS 4 (API 34) or higher image | Installed from Android Studio → SDK Manager |
| Git            | any                               | https://git-scm.com                        |

> **How to verify Git is installed?**
> ```bash
> git --version
> ```

You also need a **Wear OS emulator** (for example, *Wear OS Small Round*) or a physical Wear OS watch.

---

## 📥 Project Creation & Installation

### 1. Clone the repository

```bash
git clone https://github.com/YOUR-USERNAME/Mood_Logger_Cesar_Sanchez.git
```

### 2. Open it in Android Studio

1. Open Android Studio.
2. Select **File > Open** and choose the `Mood_Logger_Cesar_Sanchez` folder.
3. Wait for the **Gradle sync** to finish without errors.

> Android Studio creates the `local.properties` file by itself with the path of your Android SDK. This file is not part of the repository.

---

## 📁 Project Structure

```
Mood_Logger_Cesar_Sanchez/
├── app/
│   ├── build.gradle.kts            # App module dependencies
│   └── src/main/
│       ├── AndroidManifest.xml     # Watch feature + standalone declaration
│       ├── java/com/example/mood_logger_cesar_sanchez/presentation/
│       │   ├── MainActivity.kt     # State, RemoteInput, launcher, `when` logic and UI
│       │   └── theme/
│       │       ├── Color.kt        # Blue color palette (12 colors)
│       │       └── Theme.kt        # Wear Material 3 color scheme and theme
│       └── res/                    # Icons and resources
├── gradle/
│   └── libs.versions.toml          # Dependency versions
├── build.gradle.kts                # Root build configuration
├── settings.gradle.kts             # Project settings
├── gradlew / gradlew.bat           # Gradle wrapper scripts
└── README.md
```

---

## 😀 Moods and Messages

| No. | Mood      | Message displayed                                                  |
|-----|-----------|--------------------------------------------------------------------|
| 1   | Stressed  | Mood Logged: Stressed. Remember to take a deep breath.             |
| 2   | Energetic | Mood Logged: Energetic! Go for a short walk.                       |
| 3   | Calm      | Mood Logged: Calm. Enjoy this peaceful moment.                     |
| 4   | Happy     | Mood Logged: Happy! Share your smile with someone today.           |
| 5   | Tired     | Mood Logged: Tired. Take a short break and drink some water.       |
| 6   | Sad       | Mood Logged: Sad. It's okay to feel this way. Talk to someone you trust. |

---

## 🛠️ Technologies Used

- [Kotlin](https://kotlinlang.org/) – Programming language
- [Jetpack Compose for Wear OS](https://developer.android.com/training/wearables/compose) – Declarative UI
- [Wear Compose Material 3](https://developer.android.com/training/wearables/compose/migrate-to-material3) – `AppScaffold`, `ScreenScaffold`, `EdgeButton`, `ListHeader`, `TransformingLazyColumn`
- [RemoteInput](https://developer.android.com/reference/kotlin/android/app/RemoteInput.Builder) with `setChoices()` – Full-screen input with predefined options
- [RemoteInputIntentHelper](https://developer.android.com/reference/kotlin/androidx/wear/input/RemoteInputIntentHelper) (`androidx.wear:wear-input`) – Opens the input screen
- `rememberSaveable` – State that survives activity recreation
- `rememberLauncherForActivityResult` – Receives the input result
- Kotlin `when` expression – Conditional logic for each mood
- [Android Studio](https://developer.android.com/studio) and Gradle – IDE and build system

---

## 🎨 Color Palette (Blue tones)

| Use                 | Name              | Value        |
|---------------------|-------------------|--------------|
| Background          | Deep navy blue    | `0xFF06142B` |
| Surface             | Night blue        | `0xFF0F2A52` |
| Main button         | Cobalt blue       | `0xFF1E6FD9` |
| General text        | Ice blue          | `0xFFE6F1FF` |
| Secondary text      | Muted ice blue    | `0xFFA9BCD8` |
| Thin borders        | Hairline blue     | `0xFF244777` |
| Stressed message    | Steel blue        | `0xFF6F9FD8` |
| Energetic message   | Electric blue     | `0xFF2D8CFF` |
| Calm message        | Pale sky blue     | `0xFF9ADCF7` |
| Happy message       | Bright sky blue   | `0xFF5CC8FF` |
| Tired message       | Grayish blue      | `0xFF8AA4C8` |
| Sad message         | Light indigo blue | `0xFF7B8CDE` |

---

## 📄 License

This project was created for academic purposes (UT Riviera Maya, Unit II – Wearable Devices).