package com.example.root.finalproject.teams.lookUpSpecifiedTeam.View

import com.example.root.finalproject.model.lookUpTeam.LookUpTeam

interface LookUpTeamDetailView {
    fun hideLoading()
    fun showLoading()
    fun geLookUpTeam(data: List<LookUpTeam>)
}