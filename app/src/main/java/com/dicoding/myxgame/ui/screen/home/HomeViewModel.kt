package com.dicoding.myxgame.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.myxgame.data.Repository
import com.dicoding.myxgame.model.Game
import com.dicoding.myxgame.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Game>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Game>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllGames(){
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getAllGame().sortedBy { it.name })
    }

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            try {
                val searchResult = repository.searchGame(newQuery).sortedBy { it.name }
                _uiState.value = UiState.Success(searchResult)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}