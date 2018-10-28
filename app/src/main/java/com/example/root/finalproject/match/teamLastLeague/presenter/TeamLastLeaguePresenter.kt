package com.example.root.finalproject.match.teamLastLeague.presenter

import com.example.root.finalproject.match.teamLastLeague.model.TeamLastLeagueResponse
import com.example.root.finalproject.match.teamLastLeague.view.TeamLastLeagueView
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamLastLeaguePresenter(
        private val view: TeamLastLeagueView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamLastLeague(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBAPI.getDataLastLeague(id)),
                        TeamLastLeagueResponse::class.java)
            }
            view.getTeamLastLeagueList(data.await().events)
            view.hideLoading()
        }
    }
}