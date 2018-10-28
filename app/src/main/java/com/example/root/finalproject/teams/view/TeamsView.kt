package com.example.root.finalproject.teams.view

import com.example.root.finalproject.teams.model.Team

interface TeamsView {
    fun hideLoading()
    fun showLoading()
    fun showTeamList(data: List<Team>)
}