package br.edu.fiap.crud.games.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.fiap.crud.core.data.Game
import br.edu.fiap.crud.games.databinding.ItemGameBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GamesListAdapter(var games: ArrayList<Game>, val action: ListAction) :
    RecyclerView.Adapter<GamesListAdapter.GameViewHolder>() {
    private var _binding: ItemGameBinding? = null

    fun updateGames(newGames: List<Game>) {
        games.clear()
        games.addAll(newGames)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        _binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size

    inner class GameViewHolder(view: ItemGameBinding) : RecyclerView.ViewHolder(view.root) {
        private val layout = view.gameLayout
        private val gameName = view.name
        private val gameProducer = view.producer
        private val gameNotes = view.notes
        private val gameDate = view.date

        fun bind(game: Game) {
            gameName.text = game.name
            gameProducer.text = game.producer
            gameNotes.text = "${game.note}..."
            val sdf = SimpleDateFormat("MMM dd, HH:mm")
            val resultDate = Date(game.updateAt)
            gameDate.text = "Last updated: ${sdf.format(resultDate)}"
            layout.setOnClickListener { action.onClick(game.id) }
        }
    }
}