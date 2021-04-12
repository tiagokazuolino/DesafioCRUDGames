package br.edu.fiap.crud.games.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.core.repository.GameRepository
import br.edu.fiap.crud.core.usecases.AddGame
import br.edu.fiap.crud.core.usecases.GetAllGames
import br.edu.fiap.crud.core.usecases.GetGame
import br.edu.fiap.crud.core.usecases.RemoveGame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope =  CoroutineScope(Dispatchers.IO)
    val repository = GameRepository(RoomGameDataSource(application))
    val useCases = UseCases(
        AddGame(repository),
        GetAllGames(repository),
        GetGame(repository),
        RemoveGame(repository)
    )

    val saved = MutableLiveData<Boolean>()
    fun saveGame(game: Game){
        coroutineScope.launch {
            useCases.addGame(game)
            saved.postValue(true)
        }
    }
}