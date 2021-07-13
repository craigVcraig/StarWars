# StarWars
#### _The Phun App_

The Phun App is a simple Android mobile application that is intended to illustrate some of 
the Android development best practices and an understanding of android components, architecture, 
and commonly used third party libraries. 

When lauching for the first time, the app fetches a list Star Wars missions from a remote server. It
caches this list into a room database, and uses the database as the data source for any subsequent 
launches. 

### Libraries Used 
- [Data Binding](https://developer.android.com/topic/libraries/data-binding) -  Declaratively binds observable data to UI elements
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) -  Build data objects that notify views when the underlying database changes.
- [Room](https://developer.android.com/training/data-storage/room) - Abstracts the app's SQL database and acts as the local cache for the missions.
- [Retrofit](https://square.github.io/retrofit/) - Type-safe Rest Client for Android that turns the remote server's HTTP interface into a Kotlin interface
- [Navigation](https://developer.android.com/guide/navigation) - Handles in-app navigation
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Manages background threads and handles asynchronous requests
- [Koin](https://insert-koin.io/) - For dependency Injection 
- [Mockk](https://mockk.io/) - For Unit Testing 
- [Glide](https://bumptech.github.io/glide/) - for image loading



### Architecture
The Phun App follows the Google-recommended MVVM architecture pattern. It also utilizes some common 
design patterns like the Repository pattern, Builder pattern, and the Singleton pattern. The project files are 
organized by utilizing the following package structure:
- adapters: Contains binding adapters and the Recyclerview Adapter
- data: Contains the database and the network files. Responsible for fetching missions and making them available to the ViewModels
- di: contains the Koin modules and sets up an instance of a Koin Application
- ui: Contains the Views and their corresponding ViewModels. The app has 2 screens, and each of them is in it's
 own package together with it's corresponding ViewModel. The viewModels are responsible for fetching the UI data from the data layer. 
    * MissionListFragment : Contains a list of all the Starwars missions from the room database
    * MissionDetails: Contains the details of a mission. You get to this fragment by tapping on any one of the missions on the missionsList page
- utilities: Containts constants and utility extension functions. 


##### Building
You can open the project in Android studio and press run.
