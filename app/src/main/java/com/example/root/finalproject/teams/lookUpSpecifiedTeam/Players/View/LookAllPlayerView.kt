package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.View

import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Model.LookAllPlayer

interface LookAllPlayerView {
    fun hideLoading()
    fun showLoading()
    fun getAllPlayer(data: List<LookAllPlayer>)
}