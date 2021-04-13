package br.edu.fiap.crud.games.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.games.framework.di.ApplicationModule
import br.edu.fiap.crud.games.framework.di.DaggerViewModuleComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope =  CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModuleComponent.builder().applicationModule(ApplicationModule(getApplication()))
            .build().inject(this)
    }

    val saved = MutableLiveData<Boolean>()

    val currentGame = MutableLiveData<Game?>()

    fun saveGame(game: Game){
        coroutineScope.launch {
            useCases.addGame(game)
            saved.postValue(true)
        }
    }

    fun getGame(id: Long){
        coroutineScope.launch {
            val game = useCases.getGame(id)
            currentGame.postValue(game)
        }
    }

    fun deleteGame(game: Game){
        coroutineScope.launch {
            useCases.removeGame(game)
            saved.postValue(true)
        }
    }
}