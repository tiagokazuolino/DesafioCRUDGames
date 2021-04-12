package br.edu.fiap.crud.games.framework

import android.content.Context
import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.core.repository.GameDataSource
import br.edu.fiap.crud.games.framework.db.DatabaseService
import br.edu.fiap.crud.games.framework.db.GameEntity

class RoomGameDataSource(context: Context) : GameDataSource {
    private val gameDao = DatabaseService.getInstance(context).gameDao()

    override suspend fun add(game: Game) = gameDao.addGameEntity(GameEntity.fromGame(game))

    override suspend fun get(id: Long): Game? = gameDao.getGameEntity(id)?.toGame()

    override suspend fun getAll(): List<Game> = gameDao.getAllGameEntity().map { it.toGame() }

    override suspend fun remove(game: Game) = gameDao.deleteGameEntity(GameEntity.fromGame(game))
}