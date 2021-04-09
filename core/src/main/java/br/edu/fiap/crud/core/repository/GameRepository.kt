package br.edu.fiap.crud.core.repository

import br.edu.fiap.crud.core.data.Game

class GameRepository(private val dataSource: GameDataSource)  {
    suspend fun addGame(game: Game) = dataSource.add(game)

    suspend fun getGameById(id: Long) = dataSource.get(id)

    suspend fun getGameList() = dataSource.getAll()

    suspend fun removeGame(game: Game) = dataSource.remove(game)
}