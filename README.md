# Weather App

This is a simple weather app that fetches and displays the latest weather data for a user's location from a list of coordinates. The location updates every 10 seconds, and the app loops back to the first location once all coordinates have been cycled through.

## Features

- Displays the current weather for the user's location based on a list of provided coordinates.
- Updates location every 10 seconds, looping back to the first location after reaching the end.
- Weather data is fetched from the Open-meteo API, which provides free access to current weather data.

## Coordinates

The following list of coordinates represents the user’s location timeline:

- 53.619653, 10.079969
- 53.080917, 8.847533
- 52.378385, 9.794862
- 52.496385, 13.444041
- 53.866865, 10.739542
- 54.304540, 10.152741
- 54.797277, 9.491039
- 52.426412, 10.821392
- 53.542788, 8.613462
- 53.141598, 8.242565

## API Integration

The app uses the [Open-meteo.com](https://open-meteo.com) weather API to fetch weather data based on latitude and longitude.

### Example Request
To fetch the weather for a specific location, you can use the following API endpoint:
https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&current_weather=true


## Architecture

The app is designed using a combination of **Clean Architecture** and the **Model-View-ViewModel (MVVM)** pattern to ensure modularity, scalability, and maintainability.

### Key Layers:

1. **Data Layer**:
  - Responsible for data retrieval and storage.
  - Includes:
    - **Local**: Manages locally stored data (e.g., caching).
    - **Remote**: Handles API calls via `WeatherApi` and `RemoteWeatherRepositoryImpl`.
  - **Repository**: Acts as a single source of truth by mediating between local and remote data sources.

2. **Domain Layer**:
  - Contains the core business logic and is independent of external frameworks.
  - Includes:
    - **Use Cases**: Encapsulate application-specific operations (e.g., `GetWeatherInfoUseCase`).
    - **Repository Interface**: Abstracts data access, enabling easy switching or extension of data sources.

3. **Presentation Layer (UI)**:
  - Handles user interaction and displays data.
  - Built using **Jetpack Compose** for a reactive and modern UI.
  - Includes:
    - **ViewModel**: Manages UI state and interacts with the Domain Layer.
    - **Composable Components**: Render UI based on the current state from the ViewModel.

### Combined Benefits of Clean Architecture and MVVM:
- **Separation of Concerns**: Each layer has a distinct responsibility, making the code easier to understand and maintain.
- **Scalability**: The modular structure allows for seamless feature addition.
- **Testability**: The Domain Layer and ViewModel can be independently tested without reliance on the UI or data sources.
- **Maintainability**: Decoupled layers ensure that changes in one part of the app have minimal impact on others.

This architecture enables the app to remain robust, flexible, and adaptable to future changes or enhancements.

### Flow:
1. The user opens the app and sees the weather data for the current location.
2. The app updates the location every 10 seconds, fetching new weather data from the Open-meteo API for each location.
3. The Repository handles fetching data from the API.
4. The ViewModel updates the UI with the weather information for each location.
5. If there is no internet, the app will display an error and gives option to retry.

## 🛠 Tech Stack

- **Kotlin**: The app is built using Kotlin, the preferred programming language for Android development.
- **Jetpack**:
    - **Jetpack Compose**: Android’s modern UI toolkit that simplifies and accelerates UI development.
    - **Android KTX**: A set of Kotlin extensions that provide concise, idiomatic Kotlin code for Android.
    - **AndroidX**: A namespace that encompasses the Android Jetpack libraries, improving upon the original Android Support Library.
    - **Lifecycle**: Manages UI-related data in a lifecycle-conscious way, preventing memory leaks and reducing boilerplate code.
    - **ViewModel**: Holds and manages UI-related data in a lifecycle-conscious manner.
- **Dagger Hilt**: A dependency injection library that reduces the boilerplate of manual dependency injection.
- **Kotlin Coroutines**: Simplifies asynchronous programming on Android by managing background tasks in a lightweight and efficient way.
- **Kotlin Flow**: A type that can emit multiple values sequentially, used for handling asynchronous data streams.
- **Retrofit**: A network client for making HTTP requests to the Open-meteo API.
- **OkHttp**: A powerful HTTP client that ensures reliable network requests.
- **GSON**: A JSON parser used for serializing and deserializing weather data.
- **Logging Interceptor**: Logs HTTP request and response data for debugging purposes.

## Screenshots

Below are some screenshots of the Weather App:

![Weather Screen](screenshots/weather_screen.png)


## Assumptions

- If the user exits the app or an error occurs, the app will restart from the first coordinate.
- The app will display basic weather information and can be extended to include more details.

## Improvements

- Add offline support to show previously fetched weather data if there is no internet connection.
- Show more detailed weather information (e.g., humidity, wind speed, etc.).
- Allow the user to see weather details for upcoming days.
- Add animations for location transitions.
- Provide options to switch between different units (e.g., Celsius to Fahrenheit, KM/H to m/s).
- Optimize images for different screen densities by converting PNG files to SVG or resizing them.
- Show the current temperature for the rest of the day starting from the current hour.


## Contributing

Feel free to fork the project and submit issues or pull requests.

