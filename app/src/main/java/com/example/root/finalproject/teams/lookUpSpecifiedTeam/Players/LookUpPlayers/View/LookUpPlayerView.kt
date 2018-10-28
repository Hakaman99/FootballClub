package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.View

import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Model.LookUpPlayer

interface LookUpPlayerView {
    fun hideLoading()
    fun showLoading()
    fun getLookUpPlayer(data: List<LookUpPlayer>)
}