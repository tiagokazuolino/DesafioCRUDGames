package br.edu.fiap.crud.games.framework.di

import br.edu.fiap.crud.games.framework.GameViewModel
import br.edu.fiap.crud.games.framework.ListViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModuleComponent {
    fun inject(gameViewModel: GameViewModel)
    fun inject(listViewModel: ListViewModel)
}