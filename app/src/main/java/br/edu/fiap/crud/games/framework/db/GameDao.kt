package br.edu.fiap.crud.games.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface GameDao {
    @Insert(onConflict = REPLACE)
    suspend fun addGameEntity(gameEntity: GameEntity)

    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun getGameEntity(id: Long): GameEntity?

    @Query("SELECT * FROM game")
    suspend fun getAllGameEntity(): List<GameEntity>

    @Delete
    suspend fun deleteGameEntity(gameEntity: GameEntity)
}
