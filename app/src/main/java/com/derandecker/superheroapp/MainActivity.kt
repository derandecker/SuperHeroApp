package com.derandecker.superheroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.derandecker.superheroapp.model.Biography
import com.derandecker.superheroapp.model.Image
import com.derandecker.superheroapp.model.SuperHero
import com.derandecker.superheroapp.ui.theme.SuperheroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SuperHeroMainScreen(
                        innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun SuperHeroMainScreen(
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: MainViewModel = viewModel(),
    state: SuperHeroUiState = viewModel.superHeroUiState,
) {
    Column(
        modifier = Modifier.padding(innerPadding.calculateTopPadding())
    ) {
        SearchRow { text -> viewModel.getSuperHero(text) }
        when (state) {
            is SuperHeroUiState.Success -> {
                SuperHeroInfoColumn(state.hero)
            }
            is SuperHeroUiState.Loading -> Text(text = "Loading")
            is SuperHeroUiState.Error -> Text(text = "Error")
        }
    }
}

@Composable
private fun SearchRow(getSuperHero: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.weight(2f),
            value = text,
            onValueChange = { text = it },
            label = {
                Text(stringResource(R.string.search))
            },
        )
        Button(
            modifier = Modifier.weight(1f),
            onClick = { getSuperHero(text) }
        ) {
            Text(stringResource(id = R.string.search))
        }
    }
}

@Composable
fun SuperHeroInfoColumn(hero: SuperHero, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        hero.name?.let { Text(text = it) }
        hero.biography?.let { Text(text = it.fullName) }
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHeroPreview() {
    SuperheroAppTheme {
        SuperHeroMainScreen(
            state = SuperHeroUiState.Success(
                SuperHero(
                    id = "999",
                    name = "Fake Hero for Preview",
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRowPreview(modifier: Modifier = Modifier) {
    SearchRow {}
}
