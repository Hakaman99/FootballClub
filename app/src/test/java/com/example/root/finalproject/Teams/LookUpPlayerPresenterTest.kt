package com.example.root.finalproject.Teams

import com.example.root.finalproject.TestContextProvider
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Model.LookUpPlayer
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Model.LookUpPlayerResponse
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Presenter.LookUpPlayerPresenter
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.View.LookUpPlayerView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LookUpPlayerPresenterTest {
    @Mock
    private lateinit var viewLookUpPlayer: LookUpPlayerView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var testLookUpPlayerPresenter: LookUpPlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testLookUpPlayerPresenter = LookUpPlayerPresenter(viewLookUpPlayer, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLookUpTeamDetail() {
        val players: MutableList<LookUpPlayer> = mutableListOf()
        val response = LookUpPlayerResponse(players)
        val id = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getLookUpPlayer(id)),
                LookUpPlayerResponse::class.java
        )).thenReturn(response)

        testLookUpPlayerPresenter.lookUpPlayer(id)

        Mockito.verify(viewLookUpPlayer).showLoading()
        Mockito.verify(viewLookUpPlayer).getLookUpPlayer(players)
        Mockito.verify(viewLookUpPlayer).hideLoading()
    }
}