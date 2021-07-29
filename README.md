# UnsplashApp
Application for showing a Unsplash photos list and the details of each one

## Description
In the main activity the app shows a Unsplash photos list by default the page '1', with '10' elements and sorting by 'Latest'. In this activity, it shows a photo picture, the author, username and the likes of each photo.

When you select one, you can see the details with a picture, the username, the description, the likes, the camera details, the location details, the photo tags and the user details.

You can go to 'Settings' in the 'Menu' and change the page, number of elements and 3 modes of sorting ('Latest', 'Oldest' and 'Popular'). When you go back, you can save or discard the changes.

The MainActivity loads the list when the application is created and when the settings are updated.

Also, there are error control showing a SnackBar with the error.

Finally, it has the following characteristics:

- Full code in Kotlin
- Request to a Unsplash API
- Anko Library
- Picasso Library (for images)
- Gson Library (for deserialization)
- Lambdas
- Extension Functions
- Application Singleton
- Delegation
- Generics
- Shared Preferences
- Coroutines
- Unit Testing
- Instrumentation Testing
- Views: RecyclerView, ImageView, TextView, EditText, AlertDialog, SnackBar
