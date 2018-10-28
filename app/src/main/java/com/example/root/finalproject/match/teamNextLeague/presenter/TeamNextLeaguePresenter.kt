package com.example.root.finalproject.match.teamNextLeague.presenter

import com.example.root.finalproject.match.teamNextLeague.model.TeamNextLeagueResponse
import com.example.root.finalproject.match.teamNextLeague.view.TeamNextLeagueView
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamNextLeaguePresenter(
        private val view: TeamNextLeagueView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamsNextLeague(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBAPI.getDataNextLeague(id)),
                        TeamNextLeagueResponse::class.java)
            }
            view.getTeamNextLeagueList(data.await().events)
            view.hideLoading()
        }
    }
}
