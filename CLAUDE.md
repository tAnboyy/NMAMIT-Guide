# CLAUDE.md - NMAMIT Map

## Project Overview

Android campus navigation app for NMAMIT (NITTE) built with Kotlin. Features interactive Google Maps, place listings, and a teacher directory.

**Package:** `com.tandevv.nmamitmap`

## Tech Stack

- **Language:** Kotlin
- **Build:** Gradle 8.14.3, AGP 8.11.2, Kotlin 2.2.20
- **Min SDK:** 24 (Android 7.0) | **Target/Compile SDK:** 35 (Android 15)
- **Key Libraries:** Google Maps, Firebase Storage, Glide, OkHttp3, Gson, Material Design

## Project Structure

```
app/src/main/java/com/tandevv/nmamitmap/
├── MapsActivity.kt              # Main map view
├── SplashScreenActivity.kt      # Launcher/splash screen
├── WithinCampusActivity.kt      # Within-campus exploration
├── OutsideCampusActivity.kt     # Outside-campus exploration
├── SearchTabActivity.kt         # Search (places)
├── SearchTabActivity2.kt        # Search (teachers)
├── place/                       # Place model, reader, response
├── teacher/                     # Teacher model, reader, response
├── PlaceListAdapter.kt          # RecyclerView adapter for places
├── TeacherListAdapter.kt        # RecyclerView adapter for teachers
├── MarkerInfoWindowAdapter.kt   # Custom map marker info windows
├── GlobalVariables.kt           # App-wide constants
├── GpsUtils.kt                  # GPS/location utilities
├── BitmapHelper.kt              # Bitmap helper for markers
└── MyComparableTeacher.kt       # Teacher sorting comparator
```

**Data files** live in `app/src/main/res/raw/` — `places.json`, `teachers.json`, `style_json.json`.

## Build & Run

```bash
./gradlew assembleDebug        # Build debug APK
./gradlew installDebug         # Install on connected device
./gradlew test                 # Run unit tests
./gradlew connectedAndroidTest # Run instrumented tests
```

## Conventions

- **View Binding** is enabled — use it instead of `findViewById`
- **Java compatibility:** 1.8
- **Kotlin code style:** Official (set in `gradle.properties`)
- **Commit messages:** Descriptive, action-verb prefix (e.g., "Update ...", "Refactor ...", "Fix ...")
- **No formal linter** configured — follow standard Kotlin/Android conventions
- **AndroidX** and **non-transitive R classes** are enabled

## Key Patterns

- Data classes for models (`Place`, `Teacher`) with Gson deserialization
- `*Reader` classes fetch JSON from raw resources or network
- `*Response` classes wrap lists for Gson parsing
- Adapters extend `RecyclerView.Adapter` with ViewBinding
- Permissions handled via Dexter library
- Images loaded from Firebase Storage URLs via Glide

## Sensitive Files

- `app/google-services.json` — Firebase config (do not commit secrets)
- API keys managed via Google Maps Secrets Gradle Plugin (`local.properties`)
