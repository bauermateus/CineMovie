package com.mbs.cinemovie.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.cinemovie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondary
    ),
    title: @Composable () -> Unit = { Text(text = stringResource(id = R.string.app_name),
        style = TextStyle(color = MaterialTheme.colorScheme.onSecondary, fontSize = 20.sp)) },
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        colors = colors,
        title = title,
        navigationIcon = navigationIcon
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun Preview() {
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.TopCenter) {
        TopBar()
    }

}