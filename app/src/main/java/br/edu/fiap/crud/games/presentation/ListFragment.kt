package br.edu.fiap.crud.games.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import br.edu.fiap.crud.games.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addGame = view.findViewById<FloatingActionButton>(R.id.addGame)
        addGame.setOnClickListener { goToGameDetails(view) }
    }

    private fun goToGameDetails(view: View, id: Long = 0L) {
        val action: NavDirections = ListFragmentDirections.actionGoToGame(id)
        val gamesListView = view.findViewById<RecyclerView>(R.id.gamesListView)
        Navigation.findNavController(gamesListView).navigate(action)
    }
}