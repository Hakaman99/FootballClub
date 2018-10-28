package com.example.root.finalproject.match

import com.example.root.finalproject.TestContextProvider
import com.example.root.finalproject.match.teamNextLeague.model.TeamNextLeague
import com.example.root.finalproject.match.teamNextLeague.model.TeamNextLeagueResponse
import com.example.root.finalproject.match.teamNextLeague.presenter.TeamNextLeaguePresenter
import com.example.root.finalproject.match.teamNextLeague.view.TeamNextLeagueView
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamNextLeaguePresenterTest {
    @Mock
    private lateinit var viewTeamsNextLeague: TeamNextLeagueView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository


    private lateinit var presenterNextLeague: TeamNextLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenterNextLeague = TeamNextLeaguePresenter(viewTeamsNextLeague, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamListNextLeague() {
        val teamsNextLeague: MutableList<TeamNextLeague> = mutableListOf()
        val response = TeamNextLeagueResponse(teamsNextLeague)
        val league = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getDataNextLeague(league)),
                TeamNextLeagueResponse::class.java
        )).thenReturn(response)

        presenterNextLeague.getTeamsNextLeague(league)

        Mockito.verify(viewTeamsNextLeague).showLoading()
        Mockito.verify(viewTeamsNextLeague).getTeamNextLeagueList(teamsNextLeague)
        Mockito.verify(viewTeamsNextLeague).hideLoading()
    }
}