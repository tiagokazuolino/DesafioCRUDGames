package br.edu.fiap.crud.games.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import br.edu.fiap.crud.games.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val checkButton =  view.findViewById<FloatingActionButton>(R.id.checkButton)
        checkButton.setOnClickListener { Navigation.findNavController(it).popBackStack() }
    }
}