package com.mbs.cinemovie.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mbs.cinemovie.MainActivity
import com.mbs.cinemovie.Movie
import com.mbs.cinemovie.composables.MovieRow
import com.mbs.cinemovie.getMovies
import com.mbs.cinemovie.navigation.MovieScreens
import com.mbs.cinemovie.ui.theme.CineMovieTheme
import com.mbs.cinemovie.composables.TopBar

@Composable
fun HomeScreen(navController: NavController) {
    Screen(navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(navController: NavController) {
    Scaffold(topBar = {
        TopBar()
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            MainContent(navController = navController)
        }

    }
}

@Composable
fun MainContent(
    navController: NavController, list: List<Movie> = getMovies()
) {
    LazyColumn {
        items(list) { item ->
            MovieRow(movie = item) { movie ->
                navController.navigate(route = MovieScreens.DetailsScreen.name + "/${movie}")
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {

    CineMovieTheme {

        // @param MainActivity() usado apenas para poder usar o preview
        Screen(navController = NavController(MainActivity()))
    }
}