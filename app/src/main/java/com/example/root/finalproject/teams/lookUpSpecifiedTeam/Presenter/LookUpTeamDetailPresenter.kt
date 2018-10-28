package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Presenter

import com.example.root.finalproject.model.lookUpTeam.LookUpTeamResponse
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.View.LookUpTeamDetailView
import com.example.root.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LookUpTeamDetailPresenter(private val view: LookUpTeamDetailView,
                                private val apiRepository: ApiRepository,
                                private val gson: Gson,
                                private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun lookUpTeam(idTeam: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBAPI.getDataLookUpTeam(idTeam)),
                        LookUpTeamResponse::class.java
                )
            }
            view.geLookUpTeam(data.await().teams)
            view.hideLoading()
        }
    }
}