package br.edu.fiap.crud.core.usecases

import br.edu.fiap.crud.core.repository.GameRepository

class GetAllGames(private val gameRepository: GameRepository) {
    suspend operator fun invoke() = gameRepository.getAllGames()
}