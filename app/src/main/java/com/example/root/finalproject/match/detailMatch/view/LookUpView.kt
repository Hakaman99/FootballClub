package com.example.root.finalproject.match.detailMatch.view

import com.example.root.finalproject.model.lookUpEvent.LookUpEvent
import com.example.root.finalproject.model.lookUpTeam.LookUpTeam

interface LookUpView {
    fun showLoading()
    fun hideLoading()
    fun getLookUpEventList(data: List<LookUpEvent>)
    fun geLookUpTeamList(dataHome: List<LookUpTeam>, dataAway: List<LookUpTeam>)
}