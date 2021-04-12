package br.edu.fiap.crud.games.framework

import br.edu.fiap.crud.core.usecases.AddGame
import br.edu.fiap.crud.core.usecases.GetAllGames
import br.edu.fiap.crud.core.usecases.GetGame
import br.edu.fiap.crud.core.usecases.RemoveGame

data class UseCases(
    val addGame: AddGame,
    val getAllGames: GetAllGames,
    val getGame: GetGame,
    val removeGame: RemoveGame
)