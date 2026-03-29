# Project Documentation: CarrilloVictor Retrofit

[Link to Github with Releases](https://github.com/victor2006v/CarrilloVictor_Retrofit)

## 1. Activities Overview

The application consists of three main activities that facilitate navigation and interaction with the JSONPlaceholder API.

### 1.1 MainActivity
- **Role:** acts as the entry point and navigation hub of the application.
- **Functionality:**
  - Provides two buttons to navigate to different sections of the app.
  - Button "Activity 1" redirects the user to `PostsActivity`.
  - Button "Activity 2" redirects the user to `PostDetailsActivity`.

### 1.2 PostsActivity (Activity 1: All Posts)
- **Role:** Displays a comprehensive list of all posts available in the API.
- **UI Components:**
  - `Button` (Fetch 100 Posts) to initiate the network request.
  - `RecyclerView` to display the list of 100 posts.
- **Workflow:**
  1. Upon clicking "Fetch 100 Posts", the app performs a **GET** request to `/posts`.
  2. The resulting list of posts is rendered in the `RecyclerView`, showing the Post ID and Title for each item.

### 1.3 PostDetailsActivity (Activity 2: Specific Post & Comments)
- **Role:** Displays detailed information for a specific post and its associated comments.
- **UI Components:**
  - `EditText` to specify a Post ID.
  - `Button` (Search) to trigger the data retrieval.
  - `TextViews` for the Post Title and Body.
  - `RecyclerView` for the list of comments.
- **Workflow:**
  1. The user enters a valid Post ID (e.g., 5).
  2. Clicking "Search" triggers two concurrent **GET** requests:
     - `/posts/{id}`: Fetches the specific post details (Title and Body).
     - `/posts/{id}/comments`: Fetches all comments linked to that specific Post ID.
  3. The UI updates to display the post's content and populates the comments list.

---

## 2. Network Operations (Retrofit)

The application uses **Retrofit 2** with the **Gson** converter for handling all network communications. The base URL is `https://jsonplaceholder.typicode.com/`.

### 2.1 API Definition (ApiService.kt)

The `ApiService` interface defines the following HTTP methods:

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/posts` | Retrieves a list of all 100 posts. |
| **GET** | `/posts/{id}` | Retrieves a single post by its unique ID. |
| **GET** | `/posts/{id}/comments` | Retrieves all comments associated with a specific Post ID. |

### 2.2 How it works

1. **Retrofit Instance:** A singleton `RetrofitClient` is used to provide a single instance of the `ApiService` throughout the app's lifecycle.
2. **Models:** Data is parsed into Kotlin data classes (`Post` and `Comment`) automatically by the Gson converter.
3. **Asynchronous Calls:** All network requests are executed asynchronously using `.enqueue()`. This ensures the UI remains responsive while waiting for the server response.
4. **Error Handling:** Basic error handling is implemented via `onResponse` checks (`isSuccessful`) and `onFailure` callbacks, displaying `Toast` messages to inform the user of network or server issues.
