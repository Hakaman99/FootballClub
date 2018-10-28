package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.root.finalproject.R.id.*
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Model.LookAllPlayer
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayersAdapter(private val player: List<LookAllPlayer>,
                     private val listener: (LookAllPlayer) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        return PlayerViewHolder(ItemPlayersUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
        p0.bindItems(player[p1], listener)

    }

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imgPlayer: ImageView = view.find(img_player)
    private val namePlayer: TextView = view.find(tv_name_player)
    private val positionPlayer: TextView = view.find(tv_position_player)

    fun bindItems(player: LookAllPlayer, listener: (LookAllPlayer) -> Unit) {
        Picasso.get().load(player.imagePLayer).into(imgPlayer)
        namePlayer.text = player.namePlayer
        positionPlayer.text = player.positionPlayer
        itemView.onClick {
            listener(player)
        }
    }
}
