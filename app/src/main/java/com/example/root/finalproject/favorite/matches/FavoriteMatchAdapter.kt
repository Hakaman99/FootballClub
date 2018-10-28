package com.example.root.finalproject.favorite.matches

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.root.finalproject.R.id.*
import com.example.root.finalproject.favorite.model.FavoriteMatch
import com.example.root.finalproject.ui.ItemLeagueUI
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.Time
import com.example.root.finalproject.utils.invisible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteMatchAdapter(private val favoriteTeamsAdapter: List<FavoriteMatch>,
                           private val listener: (FavoriteMatch) -> Unit) :
        RecyclerView.Adapter<FavoriteMatchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(ItemLeagueUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = favoriteTeamsAdapter.size

    override fun onBindViewHolder(p0: FavoriteMatchViewHolder, p1: Int) {
        p0.bindItems(favoriteTeamsAdapter[p1], listener)
    }

}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateLeagueUI: TextView = view.find(tvDateLeagueUI)
    private val homeTeam: TextView = view.find(strHomeTeam)
    private val scores: TextView = view.find(score)
    private val awayTeam: TextView = view.find(strAwayTeam)
    private val alarms: ImageView = view.find(alarm)

    fun bindItems(teamsFavorite: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
        val dateTime = Time.formatUTCtoGMT(teamsFavorite.dateEvent, teamsFavorite.time.substring(0, 5), Constant.DATE_TIME)
        alarms.invisible()

        dateLeagueUI.text = dateTime
        homeTeam.text = teamsFavorite.homeTeam ?: ""
        scores.text = "${teamsFavorite.homeScore ?: ""} VS ${teamsFavorite.awayScore ?: ""}"
        awayTeam.text = teamsFavorite.awayTeam ?: ""
        itemView.onClick {
            listener(teamsFavorite)
        }

    }


}