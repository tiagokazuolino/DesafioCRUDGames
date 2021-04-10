package br.edu.fiap.crud.games.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import br.edu.fiap.crud.games.R
import br.edu.fiap.crud.games.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addGame.setOnClickListener { goToGameDetails() }
    }

    private fun goToGameDetails(id: Long = 0L) {
        val action: NavDirections = ListFragmentDirections.actionGoToGame(id)
        Navigation.findNavController(binding.gamesListView).navigate(action)
    }
}