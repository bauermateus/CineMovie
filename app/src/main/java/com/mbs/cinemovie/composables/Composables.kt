package com.mbs.cinemovie.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.mbs.cinemovie.Movie
import com.mbs.cinemovie.getMovies

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .padding(horizontal = 4.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp)
                    .align(Alignment.Top),
                shape = CircleShape,
                shadowElevation = 4.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current).data(movie.images[0])
                            .crossfade(200).transformations(CircleCropTransformation()).build()
                    ), contentDescription = ""
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Released: ${movie.year}", style = MaterialTheme.typography.titleMedium
                )

                AnimatedVisibility(visible = isExpanded) {
                    Column {
                        Text(
                            text = "Genre: ${movie.genre}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Box(modifier = Modifier
                    .width(140.dp)
                    .clickable {
                        isExpanded = !isExpanded
                    }) {
                    Icon(
                        imageVector = if (!isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(25.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailsMovieColumn(movie: Movie) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 64.dp)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 4.dp
            ) {
                AsyncImage(model = movie.images[0], contentDescription = "")
            }

            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Director: ${movie.director}", style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Released: ${movie.year}", style = MaterialTheme.typography.titleMedium
            )

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Genre: ${movie.genre}", style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Actors: ${movie.actors}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Rating: ${movie.rating}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Plot: ${movie.plot}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Center) {
                Box(modifier = Modifier
                    .width(140.dp)
                    .clickable {
                        isExpanded = !isExpanded
                    }
                ) {
                    Icon(
                        imageVector = if (!isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(25.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ComposablesPreview() {
    DetailsMovieColumn(movie = getMovies().first {
        it.id == "tt0499549"
    })
}