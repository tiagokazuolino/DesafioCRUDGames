package br.edu.fiap.crud.games.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.fiap.crud.games.databinding.FragmentListBinding
import br.edu.fiap.crud.games.framework.ListViewModel

class ListFragment : Fragment(), ListAction {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val gamesListAdapter = GamesListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gamesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = gamesListAdapter
        }

        binding.addGame.setOnClickListener { goToGameDetails() }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.games.observe(viewLifecycleOwner, Observer { gamesList ->
            binding.loadingView.visibility = View.GONE
            binding.gamesListView.visibility = View.VISIBLE
            gamesListAdapter.updateGames(gamesList.sortedBy { it.updateAt })
            binding.gamesSizeView.text = "Size of Games list: ${gamesList.size}"
        })
    }

    private fun goToGameDetails(id: Long = 0L) {
        val action: NavDirections = ListFragmentDirections.actionGoToGame(id)
        Navigation.findNavController(binding.gamesListView).navigate(action)
    }

    override fun onClick(id: Long) {
        goToGameDetails(id)
    }
    override fun onResume() {
        super.onResume()
        viewModel.getGames()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}