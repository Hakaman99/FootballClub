package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Presenter

import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Model.LookUpPlayerResponse
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.View.LookUpPlayerView
import com.example.root.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LookUpPlayerPresenter(
        private val view: LookUpPlayerView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun lookUpPlayer(idPlayer: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBAPI.getLookUpPlayer(idPlayer)),
                        LookUpPlayerResponse::class.java)
            }
            view.getLookUpPlayer(data.await().players)
            view.hideLoading()
        }
    }
}