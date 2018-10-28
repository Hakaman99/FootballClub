package com.example.root.finalproject.match.teamNextLeague.view

import com.example.root.finalproject.match.teamNextLeague.model.TeamNextLeague

interface TeamNextLeagueView {
    fun hideLoading()
    fun showLoading()
    fun getTeamNextLeagueList(data: List<TeamNextLeague>)

}