package com.mbs.cinemovie.screens.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mbs.cinemovie.MainActivity
import com.mbs.cinemovie.Movie
import com.mbs.cinemovie.composables.DetailsMovieColumn
import com.mbs.cinemovie.composables.TopBar
import com.mbs.cinemovie.getMovies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, id: String?) {

    val filteredMovie = getMovies().first {
        it.id == id
    }
    Scaffold(
        topBar = {
            TopBar(navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }, content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back arrow",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                })
            })
        }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(state = ScrollState(0))
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                DetailsMovieColumn(movie = filteredMovie)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")
                HorizontalScrollableImageView(filteredMovie)
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .width(240.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                AsyncImage(model = image, contentDescription = "", alignment = Center)
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    DetailsScreen(navController = NavController(MainActivity()), id = "tt0499549")
}