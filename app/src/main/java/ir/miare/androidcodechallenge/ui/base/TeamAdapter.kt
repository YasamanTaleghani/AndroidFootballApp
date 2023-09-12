package ir.miare.androidcodechallenge.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.miare.androidcodechallenge.data.model.FakeData
import ir.miare.androidcodechallenge.databinding.ItemGameBinding

class TeamAdapter(private var gameList: ArrayList<FakeData> = arrayListOf()):
    RecyclerView.Adapter<TeamAdapter.ViewHolder>(){

    lateinit var binding: ItemGameBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setGame(newGameList: ArrayList<FakeData>) {
        gameList.clear()
        gameList.addAll(newGameList)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FakeData) {
            binding.leagueName.text = item.league.name
            binding.leagueCountry.text = item.league.country
            binding.rank1.text = item.players[0].team.rank.toString()
            binding.playerName1.text = item.players[0].name
            binding.teamName1.text = item.players[0].team.name
            binding.rank2.text = item.players[1].team.rank.toString()
            binding.playerName2.text = item.players[1].name
            binding.teamName2.text = item.players[1].team.name
            binding.rank3.text = item.players[2].team.rank.toString()
            binding.playerName3.text = item.players[2].name
            binding.teamName3.text = item.players[2].team.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gameList[position])
    }
}