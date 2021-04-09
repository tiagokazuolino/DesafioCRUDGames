package br.edu.fiap.crud.core.usecases

import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.core.repository.GameRepository

class AddGame(private val gameRepository: GameRepository) {
    suspend operator fun invoke(game: Game) = gameRepository.addGame(game)
}