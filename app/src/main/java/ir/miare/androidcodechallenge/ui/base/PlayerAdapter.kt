package ir.miare.androidcodechallenge.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.miare.androidcodechallenge.data.model.FakeData
import ir.miare.androidcodechallenge.databinding.ItemPlayerBinding

class PlayerAdapter(private var playerList: ArrayList<FakeData> = arrayListOf()):
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    lateinit var binding: ItemPlayerBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setPlayers(newPlayerList: ArrayList<FakeData>) {
        playerList.clear()
        playerList.addAll(newPlayerList)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FakeData) {
            binding.rank.text = item.players[0].team.rank.toString()
            binding.playerName.text = item.players[0].name
            binding.teamName.text = item.players[0].team.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(playerList[position])
    }
}