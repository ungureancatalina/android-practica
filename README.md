## Android Learning

This is a collection of standalone Android apps demonstrating various features, UI patterns, and Kotlin/Jetpack Compose implementations.

---


## Technologies Used

| Tool / Language               | Purpose                                                                 |
|-------------------------------|-------------------------------------------------------------------------|
| Kotlin                        | Primary programming language for all Android apps                       |
| Android Studio                | IDE for building and testing Android apps                               |
| XML                           | Designing UI layouts in traditional Android apps                        |
| Jetpack Compose               | Modern declarative UI framework for Android                             |
| Intents                       | Navigation and passing data between activities                          |
| Activity Lifecycle Methods    | Managing app state across activity lifecycle (onCreate, onStart, etc.)  |
| SharedPreferences             | Simple key-value local storage (e.g., username, theme preference)       |
| Room Database                 | Local database for structured storage (e.g., Chat, Messages, Weather)   |
| Retrofit                      | HTTP client for fetching API data (WeatherTab)                          |
| MVVM Architecture             | Organizing code into Model-View-ViewModel structure                     |
| Coroutines & Flow             | Asynchronous programming and reactive data handling                     |
| MediaPipe GenAI LLM           | On-device AI model for chatbot responses                                |
| Git & GitHub                  | Version control and collaborative development                           |
| API Integration               | Fetching weather data from Open-Meteo                                   |


---

## Features

### first_app
- Install & configure Android Studio.
- Understand project structure (manifests/, java/, res/).
- Create basic layouts using ConstraintLayout & LinearLayout.
- Work with UI elements: TextView, EditText, Button, ImageView.
- Handle events with setOnClickListener.
- Pass data between activities using Intents.
- Manage activity lifecycle methods (onCreate, onStart, onResume, etc.).
- Save UI state using onSaveInstanceState.
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
- @Composable item rows with Modifier.clickable for click handling.
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

### ChatBot
- Collaborative GitHub project.
- ChatGPT-style app using MediaPipe GenAI LLM Inference (on-device model).
- Two screens:
  - Conversations list with “New Chat” button
  - Chat screen with message list + text input
- Data saved locally in Room (entities: Chat, Message).
- MVVM + Repository architecture with coroutines/Flow.
- UI(Compose).
- Focus on: saving entities, displaying lists, connecting DB ↔ UI, handling message send + LLM reply.

---

## This is how the apps look like:


<img width="375" height="846" alt="Screenshot 2025-09-14 104019" src="https://github.com/user-attachments/assets/4e501769-3d39-479e-8e67-fab484ed4a57" />
<img width="366" height="840" alt="Screenshot 2025-09-14 104144" src="https://github.com/user-attachments/assets/72af296b-0007-41d4-bed7-f3af5a54432b" />
<img width="379" height="849" alt="Screenshot 2025-09-14 104205" src="https://github.com/user-attachments/assets/fbdc1854-e55b-49b5-960a-5c2afb4d1f4a" />
<img width="371" height="829" alt="Screenshot 2025-09-14 104217" src="https://github.com/user-attachments/assets/7b41a44e-8ebe-446f-abfb-6e2c9b1ef6a9" />
<img width="372" height="839" alt="Screenshot 2025-09-14 104223" src="https://github.com/user-attachments/assets/2df562cd-d7b0-4335-8053-59eb9b441c89" />
<img width="361" height="821" alt="Screenshot 2025-09-14 103706" src="https://github.com/user-attachments/assets/3d645454-3579-42bc-8490-f46fa5eebc6e" />
<img width="360" height="813" alt="Screenshot 2025-09-14 103717" src="https://github.com/user-attachments/assets/00ca5e90-3852-42f1-8ff1-cd04bc218ea1" />
<img width="367" height="829" alt="Screenshot 2025-09-14 103912" src="https://github.com/user-attachments/assets/99be46d2-60bc-4b20-a966-d12413e66dff" />

