# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```bash
# Build the project
./gradlew assembleDebug

# Run unit tests
./gradlew test

# Run a single unit test
./gradlew :app:test --tests "com.qwin.moviematch.ExampleUnitTest.addition_isCorrect"

# Run instrumented tests (emulator/device required)
./gradlew connectedAndroidTest

# Lint
./gradlew lint
```

## Target Architecture

The project follows **Clean Architecture** with 3 layers, to be implemented under `app/src/main/java/com/qwin/moviematch/`:

```
data/       → Data sources (Retrofit, Room, TMDB API)
domain/     → Use cases, business models, repository interfaces
ui/         → Composables, ViewModels, UI state
```

**Expected data flow:** TMDB API → Repository (data layer) → Use Case (domain) → ViewModel → Compose UI via `StateFlow`/`Flow`.

## Tech Stack

- **UI:** Jetpack Compose + Material3, edge-to-edge enabled
- **Navigation:** Navigation 3 (experimental/alpha API)
- **DI:** Hilt (not yet added to `build.gradle.kts`)
- **Networking:** Retrofit + OkHttp against the TMDB API
- **Images:** Coil
- **Local cache:** Room
- **Async:** Coroutines + Flow
- **Testing:** MockK, JUnit 5, Compose Testing Library

## Current State

The project is at its initial stage (Android Studio boilerplate). Only `MainActivity`, the Compose theme (`ui/theme/`), and base dependencies are in place. Hilt, Retrofit, Room, Coil, and Navigation 3 **are not yet declared** in `gradle/libs.versions.toml` and `app/build.gradle.kts` — they still need to be added.
