package br.edu.fiap.crud.core.usecases

import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.core.repository.GameRepository

class GetGame(private val gameRepository: GameRepository) {
    suspend operator fun invoke(id: Long) = gameRepository.getGameById(id)
}