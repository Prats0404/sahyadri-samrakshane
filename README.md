# Sahyadri Samrakshane

**Sahyadri Samrakshane** is a mobile application built for citizen-science and conservation efforts in the Sahyadri (Western Ghats) region. This application empowers volunteers, researchers, and local communities to participate actively in data collection, incident reporting, and environmental tracking to preserve the biodiversity of the region.

## 🚀 Features

* **User Authentication**: Secure user login and registration powered by **Firebase Authentication**.
* **Real-time Data Sync & Cloud Storage**: Uses **Supabase** as the primary backend and PostgreSQL database, seamlessly syncing data in real-time.
* **Offline Support & Local Database**: Powered by **Room Database** to store data locally when the device is offline, ensuring uninterrupted usage in remote areas.
* **Background Synchronization**: Implements **WorkManager** to automatically sync locally saved data to the Supabase backend once the device regains internet connectivity.
* **Location Tracking**: Tracks accurate geographical coordinates utilizing custom `LocationTracker` for logging incidents and sightings accurately.
* **Modern UI**: Designed fully using **Jetpack Compose** following Material Design 3 guidelines for a premium and dynamic user interface.

## 🛠 Tech Stack & Architecture

* **Language**: Kotlin
* **UI Toolkit**: Jetpack Compose
* **Architecture**: MVVM (Model-View-ViewModel) paired with Clean Architecture principles.
* **Dependency Injection**: Dagger Hilt
* **Asynchronous Programming**: Kotlin Coroutines & Flow
* **Local Storage**: Room Database
* **Backend Integration**: 
  * Supabase (PostgreSQL, Realtime DB)
  * Firebase (Authentication)
* **Background Processing**: Android WorkManager
* **Networking**: Retrofit / Ktor (via Supabase Kotlin SDK)
* **Navigation**: Jetpack Navigation Compose

## 📂 Project Structure

```
Sahyadri_samrakshane/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/sahyadri/samrakshane/
│           │   ├── data/           # Repositories, Local DB (Room), Remote Data Sources
│           │   ├── domain/         # Models, Use Cases, Interfaces (LocationTracker)
│           │   ├── di/             # Hilt Dependency Injection Modules
│           │   ├── presentation/   # UI, ViewModels, Compose Screens, Navigation
│           │   └── worker/         # Background Sync Workers
│           └── res/                # XML resources, Themes, Drawables
├── docs/                           # Documentation and Web Assets
├── build.gradle.kts                # Project Build configurations
└── ...
```

## ⚙️ Getting Started & Setup

### Prerequisites
* Android Studio (Latest Version)
* Java Development Kit (JDK 17+)
* Android SDK (API Level 24+)

### Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/Prats0404/Sahyadri-Samrakshane.git
   ```
2. **Open the project in Android Studio.**
3. **Configure Firebase:**
   * Create a Firebase project.
   * Add an Android app and place the generated `google-services.json` file inside the `app/` directory.
   * Enable Authentication (Email/Password or Google Sign-in).
4. **Configure Supabase:**
   * Setup a Supabase project and create the necessary tables.
   * Add your Supabase `URL` and `Anon Key` to your `local.properties` or environment variables as required by the `di` modules.
5. **Build and Run:**
   * Sync the Gradle files.
   * Connect an Android device or start an emulator.
   * Click "Run" in Android Studio (`Shift + F10`).

## 🤝 Contribution Guidelines
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project.
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`).
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the Branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

## 📝 License
Distributed under the MIT License. See `LICENSE` for more information.
