# Retrofit, Room, LiveData, Koin, MVVM Sample App

This Android app demonstrates the following features:
- Fetching data from an internet API using Retrofit
- Saving data locally using Room
- Displaying data in a RecyclerView with an ad after every 2nd item
- Observing data changes using LiveData
- Dependency injection using Koin
- MVVM architecture

## Setup

To run the app, you will need to provide your own API key for the data source. You can obtain a free API key by following the instructions on the data source website. Once you have your API key, you need to add it to the `constant` file in the root of the project. Add the following line to the file with your API key:


## Libraries Used

This project utilizes the following libraries:
- Retrofit: for making network requests
- Room: for local data persistence
- LiveData: for observing data changes
- Koin: for dependency injection
- RecyclerView: for displaying a list of items with ads

## Architecture

This project follows the MVVM (Model-View-ViewModel) architecture pattern. The app is split into three main layers:
- Model: contains the data model classes and the repositories for fetching and storing the data.
- View: contains the UI components, such as activities and fragments, and the adapters for displaying the data in a RecyclerView.
- ViewModel: contains the ViewModel classes that handle the logic for the UI components and communicate with the repositories to fetch and store the data.

## Contributors

Contributions to this project are welcome. If you would like to contribute, please fork the repository and submit a pull request.

