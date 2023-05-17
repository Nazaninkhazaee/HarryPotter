# Harry Potter Demo Application

Harry Potter is a small demo application that utilizes the Harry Potter API to showcase various features and best practices in Android development.

## Project Characteristics

- 100% Kotlin
- Model-View-ViewModel (MVVM) architecture
- Repository Pattern
- Single activity architecture
- Android Jetpack components
- Material Design principles
- DiffCallback for efficient list updates

## Libraries

The project incorporates the following libraries:

- **Coroutines**: For managing background threads and asynchronous programming.
  - **ViewModel**: Stores UI-related data that persists across configuration changes.
  - **WorkManager**: A recommended API for background processing.
  - **LiveData**: Builds data objects that notify views when the underlying database changes.
  - **Lifecycle**: Creates a UI that automatically responds to lifecycle events.
  - **Navigation**: Handles everything needed for in-app navigation.
  - **Room**: Provides an abstraction layer over SQLite for database operations with compile-time checks.
- **Retrofit**: A type-safe HTTP client for making network requests to the Harry Potter API.
- **Glide**: An image loading and caching library for displaying images efficiently.
- **Timber**: A logger with a small, extensible API for better logging and debugging.
- **Moshi**: A modern JSON library for Android, Java, and Kotlin for parsing JSON responses.

## Installation

To run the Harry Potter demo application locally, follow these steps:

1. Clone the repository to your local machine:

2. Open the project using Android Studio.

3. Build and run the application on an emulator or a connected device.

## Contributing

Contributions to the Harry Potter demo application are welcome and encouraged! If you have any bug reports, feature requests, or suggestions, please open an issue on the project's GitHub repository. Additionally, feel free to submit pull requests with improvements or fixes.

Before contributing, please review the [Contribution Guidelines](CONTRIBUTING.md) for more information.

## Acknowledgements

The Harry Potter demo application was created with inspiration from the Harry Potter API (https://www.potterapi.com/). We would like to acknowledge the creators of the API for their valuable data and resources.

## Contact

For any inquiries or questions regarding the Harry Potter demo application, please reach out to [your-email@example.com](mailto:your-email@example.com).



