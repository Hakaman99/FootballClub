package com.example.root.finalproject.Teams

import com.example.root.finalproject.TestContextProvider
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.teams.model.Team
import com.example.root.finalproject.teams.model.TeamResponse
import com.example.root.finalproject.teams.presenter.TeamsPresenter
import com.example.root.finalproject.teams.view.TeamsView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {
    @Mock
    private lateinit var viewTeams: TeamsView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenterTeams: TeamsPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenterTeams = TeamsPresenter(viewTeams, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getTeams(league)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenterTeams.getTeamList(league)

        Mockito.verify(viewTeams).showLoading()
        Mockito.verify(viewTeams).showTeamList(teams)
        Mockito.verify(viewTeams).hideLoading()
    }
}