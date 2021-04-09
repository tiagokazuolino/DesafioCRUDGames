package br.edu.fiap.crud.core.usecases

import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.core.repository.GameRepository

class RemoveGame(private val gameRepository: GameRepository) {
    suspend operator fun invoke(game: Game) = gameRepository.removeGame(game)
}