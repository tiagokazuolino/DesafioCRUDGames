package br.edu.fiap.crud.games.framework.di

import br.edu.fiap.crud.core.repository.GameRepository
import br.edu.fiap.crud.core.usecases.AddGame
import br.edu.fiap.crud.core.usecases.GetAllGames
import br.edu.fiap.crud.core.usecases.GetGame
import br.edu.fiap.crud.core.usecases.RemoveGame
import br.edu.fiap.crud.games.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: GameRepository) = UseCases(
        AddGame(repository),
        GetAllGames(repository),
        GetGame(repository),
        RemoveGame(repository)
    )
}