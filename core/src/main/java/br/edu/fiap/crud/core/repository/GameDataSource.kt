package br.edu.fiap.crud.core.repository

import br.edu.fiap.crud.core.data.Game

interface GameDataSource {
    suspend fun add(game: Game)
    suspend fun get(id: Long): Game?
    suspend fun getAll(): List<Game>
    suspend fun remove(game: Game)
}