package com.example.superheroes_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes_app.data.HeroesRepositiory
import com.example.superheroes_app.model.Hero
import com.example.superheroes_app.ui.theme.SuperheroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroApp(modifier: Modifier = Modifier){
    val content: List<Hero> = HeroesRepositiory().loadData()
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                modifier = Modifier.fillMaxWidth()
            )
        }
    ){
        LazyColumn(
            contentPadding = it,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            items(content) {hero ->
                SuperheroListItem(
                    hero = hero,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun SuperheroListItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .height(76.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            ListItemDescription(
                nameRes = hero.nameRes,
                descriptionRes = hero.descriptionRes,
                modifier = Modifier.weight(0.7f)
            )
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(id = hero.nameRes),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(start = dimensionResource(id = R.dimen.padding_medium))
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.small)
            )
        }
    }
}

@Composable
fun ListItemDescription(
    @StringRes nameRes: Int,
    @StringRes descriptionRes: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = nameRes),
            style = MaterialTheme.typography.displaySmall,
        )
        Text(
            text = stringResource(id = descriptionRes),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
        )
    }
}


@Composable
fun AppBar(modifier: Modifier = Modifier){
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.displayLarge,
        modifier = modifier.wrapContentSize(Alignment.Center)
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperheroesAppTheme {
        SuperheroApp(modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_small))
        )
    }
}