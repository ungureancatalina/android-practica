# Android Learning

This repository contains separate Android applications, each as an individual project.  

---

## Structure

### first_app
- Install & configure Android Studio.
- Understand project structure (`manifests/`, `java/`, `res/`).
- Create basic layouts using ConstraintLayout & LinearLayout.
- Work with UI elements: TextView, EditText, Button, ImageView.
- Handle events with `setOnClickListener`.
- Pass data between activities using Intents.
- Manage activity lifecycle methods (`onCreate`, `onStart`, `onResume`, etc.).
- Save UI state using `onSaveInstanceState`.
- Build small functional apps:  
  - Login/sign-up with welcome page  
  - BMI calculator  
  - Parity checker (even/odd numbers)

### xml_parity_check
- Simple app to read user input and check if a number is even or odd.
- Focus on:
  - Reading from EditText
  - Converting string input to integer
  - Displaying results via TextView or Toast

### RecyclerView
- Implementing the Adapter pattern in Android.
- Creating custom adapters for RecyclerView.
- Handling click events on list items (Toast message with movie title).
- Displaying a list of Movie objects.
- **Data Storage**:
  - Using SharedPreferences for simple key-value storage (e.g., saved username, theme preference).
  - Introduction to Room database (concept only, no full implementation).
  - Implementing a settings screen to toggle dark/light theme.
  - Storing username after login and auto-filling it on app restart (password entered each time).

### RecyclerViewCompose
- Same functionality as RecyclerView/ but implemented with Jetpack Compose.
- Using LazyColumn to display lists.
- `@Composable` item rows with `Modifier.clickable` for click handling.
- Showing Toast/Snackbar on item click.
- **Data Storage**:
  - Same SharedPreferences features as in RecyclerView/, but implemented in Compose.
    
### WeatherTab
- Added BottomNavigation with a new tab dedicated to weather.
- Screen displays the weekly forecast in a clear and structured UI.
- Data is fetched from the Open-Meteo API using Retrofit.
- API results are stored in a Room database (dedicated table).
- UI reads exclusively from the database for consistency.
- Implemented a Refresh button to:
  - Request fresh data from the API
  - Update the Room table
  - Automatically reload the UI with the new forecast
- Uses Repository + ViewModel + coroutines/Flow to manage and observe data.

  
