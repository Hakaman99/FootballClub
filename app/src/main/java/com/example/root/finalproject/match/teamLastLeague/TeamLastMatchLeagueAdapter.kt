package com.example.root.finalproject.match.teamLastLeague

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.root.finalproject.R.id.*
import com.example.root.finalproject.match.teamLastLeague.model.TeamLastLeague
import com.example.root.finalproject.ui.ItemLeagueUI
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.Time
import com.example.root.finalproject.utils.invisible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamLastMatchLeagueAdapter(private val teamsLastMatch: List<TeamLastLeague>,
                                 private val listener: (TeamLastLeague) -> Unit)
    : RecyclerView.Adapter<TeamLastMatchViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamLastMatchViewHolder {
        return TeamLastMatchViewHolder(ItemLeagueUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = teamsLastMatch.size

    override fun onBindViewHolder(p0: TeamLastMatchViewHolder, p1: Int) {
        p0.bindItems(teamsLastMatch[p1], listener)
    }


}

class TeamLastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateLeagueUI: TextView = view.find(tvDateLeagueUI)
    private val homeTeam: TextView = view.find(strHomeTeam)
    private val scores: TextView = view.find(score)
    private val awayTeam: TextView = view.find(strAwayTeam)
    private val alarms: ImageView = view.find(alarm)

    fun bindItems(teamsLastMatch: TeamLastLeague, listener: (TeamLastLeague) -> Unit) {
        val dateTime = Time.formatUTCtoGMT(teamsLastMatch.dateEvent, teamsLastMatch.time.substring(0, 5), Constant.DATE_TIME)
        alarms.invisible()
        dateLeagueUI.text = dateTime
        homeTeam.text = teamsLastMatch.homeTeam
        scores.text = "${teamsLastMatch.homeScore ?: ""} VS ${teamsLastMatch.awayScore ?: ""}"
        awayTeam.text = teamsLastMatch.awayTeam
        itemView.onClick {
            listener(teamsLastMatch)
        }
    }


}
