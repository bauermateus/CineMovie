package com.mbs.cinemovie.navigation

enum class MovieScreens {
    HomeScreen,
    DetailsScreen;
    companion object {
        fun fromRoute(route: String?) {
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route not valid.")
            }
        }
    }
}