package com.dicoding.myxgame.data

import android.content.Context
import com.dicoding.myxgame.model.FakeDataset
import com.dicoding.myxgame.model.FakeFavGame
import com.dicoding.myxgame.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository (context: Context){

    private var games = mutableListOf<Game>()
    private val game = FakeDataset.gameDataset
    private var favoriteGames = mutableListOf<Game>()

    init {
        if (games.isEmpty()) {
            val newData = game
            games.addAll(newData)
        }
        if (favoriteGames.isEmpty()) {
            favoriteGames = FakeFavGame.dummyFavGame
        }
    }

    fun getAllGame(): List<Game> {
        return FakeDataset.gameDataset
    }

    fun getGameById(id: Long): Game {
        return FakeDataset.gameDataset.first {
            it.id == id
        }
    }

    fun searchGame(query: String): List<Game>{
        return FakeDataset.gameDataset.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

//    fun saveFavGame(game: Game) {
//        favoriteGames.add(game)
//    }

//    fun getAllFavGames(): Flow<List<Game>> = flowOf(favoriteGames)
//
//    fun deleteFavGameById(gameId: Long) {
//        favoriteGames.removeIf { it.id == gameId}
//    }



    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(context: Context): Repository =
            instance ?: synchronized(this) {
                Repository(context).apply {
                instance = this
        }   }
    }
}