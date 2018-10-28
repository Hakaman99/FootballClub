package com.example.root.finalproject.match.teamLastLeague.view

import com.example.root.finalproject.match.teamLastLeague.model.TeamLastLeague


interface TeamLastLeagueView {
    fun showLoading()
    fun hideLoading()
    fun getTeamLastLeagueList(data: List<TeamLastLeague>)
}