package com.example.root.finalproject.match.detailMatch.presenter

import com.example.root.finalproject.match.detailMatch.view.LookUpView
import com.example.root.finalproject.model.lookUpEvent.LookUpEventResponse
import com.example.root.finalproject.model.lookUpTeam.LookUpTeamResponse
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class LookUpPresenter(
        private val view: LookUpView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLookUpEvent(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBAPI.getDataLookUpEvent(id)),
                        LookUpEventResponse::class.java)
            }
            view.getLookUpEventList(data.await().events)
        }
    }

    fun getLookUpTeam(idHome: String, idAway: String) {
        async(context.main) {
            val data1 = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBAPI.getDataLookUpTeam(idHome)),
                        LookUpTeamResponse::class.java)
            }

            val data2 = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBAPI.getDataLookUpTeam(idAway)),
                        LookUpTeamResponse::class.java)
            }
            view.geLookUpTeamList(data1.await().teams, data2.await().teams)
            view.hideLoading()
        }
    }
}