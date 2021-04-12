package br.edu.fiap.crud.games.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.games.R
import br.edu.fiap.crud.games.databinding.FragmentGameBinding
import br.edu.fiap.crud.games.framework.GameViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameViewModel
    private var currentGame = Game("", "", "", "", 0L, 0L)

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
        binding.checkButton.setOnClickListener {
            if (
                binding.etGameName.text.toString() != "" ||
                binding.etGameNote.text.toString() != "" ||
                binding.etGamePlatform.text.toString() != "" ||
                binding.etGameProducer.text.toString() != ""
            ) {
                val time: Long = System.currentTimeMillis()
                currentGame.name = binding.etGameName.text.toString()
                currentGame.note = binding.etGameNote.text.toString()
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

    private fun observeViewModel(){
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if(it) {
                Toast.makeText(context, "Salvo!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(binding.etGameName).popBackStack()
            } else {
                Toast.makeText(context, "Houve um erro tente novamente!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun hideKeyboard(){
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etGameName.windowToken, 0)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}