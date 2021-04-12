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
import br.edu.fiap.crud.games.databinding.FragmentGameBinding
import br.edu.fiap.crud.games.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addGame.setOnClickListener { goToGameDetails() }
    }

    private fun goToGameDetails( id: Long = 0L) {
        val action: NavDirections = ListFragmentDirections.actionGoToGame(id)
        Navigation.findNavController(binding.gamesListView).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}