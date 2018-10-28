package com.example.root.finalproject.match

import com.example.root.finalproject.TestContextProvider
import com.example.root.finalproject.match.detailMatch.presenter.LookUpPresenter
import com.example.root.finalproject.match.detailMatch.view.LookUpView
import com.example.root.finalproject.model.lookUpEvent.LookUpEvent
import com.example.root.finalproject.model.lookUpEvent.LookUpEventResponse
import com.example.root.finalproject.model.lookUpTeam.LookUpTeam
import com.example.root.finalproject.model.lookUpTeam.LookUpTeamResponse
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LookUpPresenterTest {
    @Mock
    private lateinit var view: LookUpView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: LookUpPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LookUpPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLookUpEvent() {
        val teamsLookUpEvent: MutableList<LookUpEvent> = mutableListOf()
        val teamsLookUp1: MutableList<LookUpTeam> = mutableListOf()
        val response1 = LookUpEventResponse(teamsLookUpEvent)
        val response2 = LookUpTeamResponse(teamsLookUp1)
        val idLookUpEvent = "1234"
        val idLookUpTeam = "1234"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getDataLookUpEvent(idLookUpEvent)),
                LookUpEventResponse::class.java
        )).thenReturn(response1)

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getDataLookUpTeam(idLookUpTeam)),
                LookUpTeamResponse::class.java
        )).thenReturn(response2)

        presenter.getLookUpEvent(idLookUpEvent)
        presenter.getLookUpTeam(idLookUpTeam, idLookUpTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).getLookUpEventList(teamsLookUpEvent)
        Mockito.verify(view).geLookUpTeamList(response2.teams, response2.teams)
        Mockito.verify(view).hideLoading()
    }
}