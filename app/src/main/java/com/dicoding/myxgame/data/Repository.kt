package com.dicoding.myxgame.data

import android.content.Context
import com.dicoding.myxgame.model.FakeDataset
import com.dicoding.myxgame.model.Game

class Repository (context: Context){

    private var games = mutableListOf<Game>()
    private val game = FakeDataset.gameDataset

    init {
        if (games.isEmpty()) {
            val newData = game
            games.addAll(newData)
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