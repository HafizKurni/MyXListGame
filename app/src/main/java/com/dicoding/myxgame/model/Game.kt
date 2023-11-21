package com.dicoding.myxgame.model

data class Game (
    val id: Long,
    val name: String,
    val console: String,
    val release: String,
    val gender: String,
    val developer: String,
    val publisher: String,
    val photoId: Int,
    val description: String
)