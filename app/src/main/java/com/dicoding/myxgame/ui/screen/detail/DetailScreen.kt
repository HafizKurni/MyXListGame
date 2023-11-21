package com.dicoding.myxgame.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.myxgame.R
import com.dicoding.myxgame.di.Injection
import com.dicoding.myxgame.model.Game
import com.dicoding.myxgame.ui.UiState
import com.dicoding.myxgame.ui.ViewModelFactory
import com.dicoding.myxgame.ui.theme.MyXGameTheme
import com.dicoding.myxgame.ui.theme.Shapes

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    gameId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateBack: () -> Unit,
) {
    viewModel.gameDetail.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getGameById(gameId)
            }

            is UiState.Success -> {

                DetailContent(
                    game = uiState.data.game,
                    modifier = modifier,
                    onBackClick = navigateBack,
                )
            }

            else -> {}
        }
    }
}

@Composable
fun DetailContent(
    game: Game,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.navigation_back),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable { onBackClick() }
        )
        Row {
            Box(modifier = Modifier.weight(1f).clip(Shapes.large)){
                Image(
                    painter = painterResource(id = game.photoId),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = game.name,
                    modifier = Modifier
                        .size(300.dp)

                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = stringResource(id = R.string.name),
                    style = MaterialTheme.typography.titleMedium)
                Text(game.name)
                Spacer(modifier = Modifier.padding(5.dp))

                Text(text = stringResource(id = R.string.console),
                    style = MaterialTheme.typography.titleMedium)
                Text(game.console)
                Spacer(modifier = Modifier.padding(5.dp))

                Text(text = stringResource(id = R.string.publisher),
                    style = MaterialTheme.typography.titleMedium)
                Text(game.publisher)
                Spacer(modifier = Modifier.padding(5.dp))

                Text(text = stringResource(id = R.string.developer),
                    style = MaterialTheme.typography.titleMedium)
                Text(game.developer)
                Spacer(modifier = Modifier.padding(5.dp))

                Text(text = stringResource(id = R.string.gender),
                    style = MaterialTheme.typography.titleMedium)
                Text(game.gender)
                Spacer(modifier = Modifier.padding(5.dp))

                Text(text = stringResource(id = R.string.release),
                    style = MaterialTheme.typography.titleMedium)
                Text(game.release)
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = game.description, textAlign = TextAlign.Justify)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    MyXGameTheme {
        DetailScreen(gameId = 6, navigateBack = { })
    }
}

