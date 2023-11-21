package com.dicoding.myxgame.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.dicoding.myxgame.data.Repository
import com.dicoding.myxgame.model.Game
import com.dicoding.myxgame.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _gameDetail: MutableStateFlow<UiState<DetailState>> = MutableStateFlow(UiState.Loading)
    val gameDetail: StateFlow<UiState<DetailState>>
        get() = _gameDetail

    fun getGameById(id: Long){
        _gameDetail.value = UiState.Loading
        _gameDetail.value = UiState.Success(DetailState(repository.getGameById(id)))
    }
}


