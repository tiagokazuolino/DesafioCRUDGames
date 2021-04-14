package br.edu.fiap.crud.games.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.games.R
import br.edu.fiap.crud.games.databinding.FragmentGameBinding
import br.edu.fiap.crud.games.framework.GameViewModel


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var gameId = 0L
    private lateinit var viewModel: GameViewModel
    private var currentGame = Game("", "", "", "", 0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        arguments?.let {
            gameId = GameFragmentArgs.fromBundle(it).gameId
        }

        if (gameId != 0L) {
            viewModel.getGame(gameId)
        }

        binding.checkButton.setOnClickListener {
            if (
                binding.etGameName.text.toString() != "" ||
                binding.etGameNote.text.toString() != "" ||
                binding.etGamePlatform.text.toString() != "" ||
                binding.etGameProducer.text.toString() != ""
            ) {
                val time: Long = System.currentTimeMillis()
                currentGame.name = binding.etGameName.text.toString()
                currentGame.nota = binding.etGameNote.text.toString()
                currentGame.platform = binding.etGamePlatform.text.toString()
                currentGame.producer = binding.etGameProducer.text.toString()
                currentGame.updateAt = time
                if (currentGame.id == 0L) {
                    currentGame.createAt = time
                }
                viewModel.saveGame(currentGame)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Salvo!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(binding.etGameName).popBackStack()
            } else {
                Toast.makeText(context, "Houve um erro tente novamente!", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.currentGame.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                currentGame = it
                binding.etGameName.setText(it.name, TextView.BufferType.EDITABLE)
                binding.etGameNote.setText(it.nota, TextView.BufferType.EDITABLE)
                binding.etGamePlatform.setText(it.platform, TextView.BufferType.EDITABLE)
                binding.etGameProducer.setText(it.producer, TextView.BufferType.EDITABLE)
            }
        })
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etGameName.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.game_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteMenu -> {
                if (context != null && gameId != 0L) {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete Game")
                        .setMessage("Are you sure what to delete this game?")
                        .setPositiveButton("Yes") { _, _ ->
                            viewModel.deleteGame(
                                currentGame
                            )
                        }
                        .setNegativeButton("No") { _, _ -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}