package com.example.root.finalproject.Teams

import com.example.root.finalproject.TestContextProvider
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Model.LookAllPlayer
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Model.LookAllPlayerResponse
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Presenter.LookAllPlayerPresenter
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.View.LookAllPlayerView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LookAllPlayerPresenterTest {
    @Mock
    private lateinit var viewLookAllPlayer: LookAllPlayerView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var testLookAllPlayerPresenter: LookAllPlayerPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testLookAllPlayerPresenter = LookAllPlayerPresenter(viewLookAllPlayer, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLookAllPlayer() {
        val player: MutableList<LookAllPlayer> = mutableListOf()
        val response = LookAllPlayerResponse(player)
        val id = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getLookAllPlayer(id)),
                LookAllPlayerResponse::class.java
        )).thenReturn(response)
        testLookAllPlayerPresenter.getLookAllPlayer(id)

        Mockito.verify(viewLookAllPlayer).showLoading()
        Mockito.verify(viewLookAllPlayer).getAllPlayer(player)
        Mockito.verify(viewLookAllPlayer).hideLoading()

    }

}