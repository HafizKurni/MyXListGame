package com.dicoding.myxgame.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.myxgame.di.Injection
import com.dicoding.myxgame.model.Game
import com.dicoding.myxgame.ui.UiState
import com.dicoding.myxgame.ui.ViewModelFactory
import com.dicoding.myxgame.ui.composes.ListItem
import com.dicoding.myxgame.ui.composes.SearchBar


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllGames()
            }

            is UiState.Success -> {
                HomeContent(
                    listGames = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = viewModel::search
                )
            }

            is UiState.Error -> {}
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    listGames: List<Game>,
    query: String,
    onQueryChange: (String) -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .weight(1f)
            )
        }

        Box{
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 80.dp),
                modifier = modifier.testTag("GameList")
            ) {
                items(listGames, key = { it.id }) { game ->
                    ListItem(
                        game = game,
                        modifier = Modifier
                            .clickable { navigateToDetail(game.id) }
                    )
                }

            }
        }
    }
}