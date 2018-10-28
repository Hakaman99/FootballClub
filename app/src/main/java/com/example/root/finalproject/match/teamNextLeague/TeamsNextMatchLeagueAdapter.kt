package com.example.root.finalproject.match.teamNextLeague

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.root.finalproject.R.id.*
import com.example.root.finalproject.match.teamNextLeague.model.TeamNextLeague
import com.example.root.finalproject.ui.ItemLeagueUI
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.Time
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamsNextMatchLeagueAdapter(private val teamsNextMatch: List<TeamNextLeague>,
                                  private val listener: (TeamNextLeague) -> Unit,
                                  private val intent: (TeamNextLeague) -> Unit) :
        RecyclerView.Adapter<TeamNextViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamNextViewHolder {
        return TeamNextViewHolder(ItemLeagueUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = teamsNextMatch.size

    override fun onBindViewHolder(p0: TeamNextViewHolder, p1: Int) {
        p0.bindItems(teamsNextMatch[p1], listener, intent)
    }
}

class TeamNextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateLeagueUI: TextView = view.find(tvDateLeagueUI)
    private val homeTeam: TextView = view.find(strHomeTeam)
    private val scores: TextView = view.find(score)
    private val awayTeam: TextView = view.find(strAwayTeam)
    private val alarms: ImageView = view.find(alarm)

    fun bindItems(teamNextMatch: TeamNextLeague, listener: (TeamNextLeague) -> Unit, intent: (TeamNextLeague) -> Unit) {
        val dateTime = Time.formatUTCtoGMT(teamNextMatch.dateEvent, teamNextMatch.time.substring(0, 5), Constant.DATE_TIME)
        dateLeagueUI.text = dateTime
        homeTeam.text = teamNextMatch.homeTeam
        scores.text = "${teamNextMatch.homeScore ?: ""} VS ${teamNextMatch.awayScore ?: ""}"
        awayTeam.text = teamNextMatch.awayTeam
        itemView.onClick {
            listener(teamNextMatch)
        }
        alarms.onClick {
            intent(teamNextMatch)
        }
    }

}
