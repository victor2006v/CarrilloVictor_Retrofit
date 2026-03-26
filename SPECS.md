# Application Specifications: CarrilloVictor Retrofit

## 1. Overview
An Android application designed to interact with the JSONPlaceholder API using Retrofit to fetch and display posts and comments.

## 2. Technical Stack
- **Language:** Kotlin
- **Networking:** Retrofit 2 + Gson
- **UI Components:** RecyclerView, ScrollView, EditText, Button
- **Architecture:** MVVM (Recommended)

## 3. Functional Requirements

### 3.1 Main Menu
- **UI:** A central screen with two primary navigation buttons.
- **Button 1:** Navigates to **Activity 1 (All Posts)**.
- **Button 2:** Navigates to **Activity 2 (Post Details & Comments)**.

### 3.2 Activity 1: All Posts
- **UI Components:**
  - `EditText` (Input Type: Number): To enter an integer.
  - `Button`: To trigger the API call.
  - `RecyclerView`: To display the list of posts.
- **Behavior:**
  - User enters an integer (optional/specified by requirements).
  - On button click, the app performs a GET request to `https://jsonplaceholder.typicode.com/posts`.
  - The UI displays all 100 posts in a scrollable `RecyclerView`.
  - Each list item should show at least the Post Title and ID.

### 3.3 Activity 2: Specific Post & Comments
- **UI Components:**
  - `EditText` (Input Type: Number): To enter a specific Post ID.
  - `Button`: To trigger the search.
  - `TextViews`: To display the specific post's Title and Body.
  - `RecyclerView`: To display the comments associated with that post.
- **Behavior:**
  - User enters a Post ID (e.g., 5).
  - On button click, the app performs two concurrent or sequential GET requests:
    1. `https://jsonplaceholder.typicode.com/posts/{id}`
    2. `https://jsonplaceholder.typicode.com/posts/{id}/comments`
  - The UI updates to show the specific post details.
  - The `RecyclerView` populates exclusively with the comments for that specific post.

## 4. Use Cases

### UC1: View All Posts
1. **Actor:** User
2. **Precondition:** App is on the Main Menu.
3. **Flow:**
   - User taps "Activity 1" button.
   - User enters a number (as per UI requirement).
   - User taps the "Fetch Posts" button.
   - App calls JSONPlaceholder API.
   - App renders 100 posts in the list.
4. **Postcondition:** User sees a scrollable list of all posts.

### UC2: View Post Comments by ID
1. **Actor:** User
2. **Precondition:** App is on the Main Menu.
3. **Flow:**
   - User taps "Activity 2" button.
   - User enters a valid Post ID (1-100) in the input field.
   - User taps the "Search" button.
   - App fetches the specific post data and its comments.
   - App displays post details and the list of comments below it.
4. **Postcondition:** User sees specific information and filtered comments for the chosen ID.

## 5. API Endpoints
- **Base URL:** `https://jsonplaceholder.typicode.com/`
- **GET** `/posts`: Returns a list of 100 posts.
- **GET** `/posts/{id}`: Returns a single post object.
- **GET** `/posts/{id}/comments`: Returns comments for a specific post.
