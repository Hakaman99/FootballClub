package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Presenter

import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Model.LookAllPlayerResponse
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.View.LookAllPlayerView
import com.example.root.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LookAllPlayerPresenter(
        private val view: LookAllPlayerView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLookAllPlayer(id: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBAPI.getLookAllPlayer(id)),
                        LookAllPlayerResponse::class.java)
            }
            view.getAllPlayer(data.await().player)
            view.hideLoading()
        }
    }
}