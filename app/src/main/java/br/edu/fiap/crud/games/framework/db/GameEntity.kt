package br.edu.fiap.crud.games.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.edu.fiap.crud.core.data.Game

@Entity(tableName = "game")
data class GameEntity(
    var name: String,
    var producer: String,
    var platform: String,
    var note: String,
    @ColumnInfo(name = "creation_date")
    var createAt: Long,
    @ColumnInfo(name = "update_time")
    var updateAt: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
) {
    companion object{
        fun fromGame(game: Game) = GameEntity(
            game.name,
            game.producer,
            game.platform,
            game.note,
            game.createAt,
            game.updateAt
        )
    }

    fun toGame() = Game(name, producer, platform, note, createAt, updateAt, id)
}