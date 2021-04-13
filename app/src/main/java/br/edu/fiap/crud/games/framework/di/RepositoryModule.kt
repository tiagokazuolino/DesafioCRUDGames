package br.edu.fiap.crud.games.framework.di

import android.app.Application
import br.edu.fiap.crud.core.repository.GameRepository
import br.edu.fiap.crud.games.framework.RoomGameDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providerRepository(app: Application) = GameRepository(RoomGameDataSource(app))
}