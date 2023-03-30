# MovieProject-Clean-Architecture-MVVM-Hilt-Flow-Tmdb-Paging3
MovieProject - Clean Architecture with MVVM, Hilt, Flow, Tmdb, Paging3
MovieProject is an Android application built with Clean Architecture, MVVM, Hilt, Flow, Tmdb, and Paging3. It provides users with the latest movies, popular movies, top-rated movies, and upcoming movies information from the Tmdb API.

Architecture
The app is based on the Clean Architecture principle, which allows for separation of concerns and maintainability. The code is organized into the following modules:

app: Contains the application class and the app-specific modules.
data: Contains the data sources and repositories.
domain: Contains the business logic and use cases.
presentation: Contains the UI layer with MVVM architecture and view models.
Technologies
Kotlin
Clean Architecture
MVVM
Hilt
Flow
Retrofit
Moshi
Tmdb API
Paging3
Glide
Features
Display latest, popular, top-rated, and upcoming movies information from the Tmdb API.
Search for movies by title.
View movie details including the synopsis, release date, and rating.
Add movies to your watchlist.
Installation
To install the app, clone the repository and open the project in Android Studio. Build and run the app on a physical device or emulator.

Note: You will need an API key from the Tmdb API to use the app. Replace the TMDB_API_KEY constant in the Constants.kt file with your own API key.

License
This project is licensed under the MIT License - see the LICENSE file for details.
