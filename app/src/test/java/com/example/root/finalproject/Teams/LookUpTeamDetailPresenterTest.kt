package com.example.root.finalproject.Teams

import com.example.root.finalproject.TestContextProvider
import com.example.root.finalproject.model.lookUpTeam.LookUpTeam
import com.example.root.finalproject.model.lookUpTeam.LookUpTeamResponse
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Presenter.LookUpTeamDetailPresenter
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.View.LookUpTeamDetailView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LookUpTeamDetailPresenterTest {
    @Mock
    private lateinit var viewLookUpTeam: LookUpTeamDetailView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository


    private lateinit var testLookUpTeamDetailPresenter: LookUpTeamDetailPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testLookUpTeamDetailPresenter = LookUpTeamDetailPresenter(viewLookUpTeam, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLookUpTeamDetail() {
        val teams: MutableList<LookUpTeam> = mutableListOf()
        val response = LookUpTeamResponse(teams)
        val id = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBAPI.getDataLookUpTeam(id)),
                LookUpTeamResponse::class.java
        )).thenReturn(response)

        testLookUpTeamDetailPresenter.lookUpTeam(id)

        Mockito.verify(viewLookUpTeam).showLoading()
        Mockito.verify(viewLookUpTeam).geLookUpTeam(teams)
        Mockito.verify(viewLookUpTeam).hideLoading()
    }
}