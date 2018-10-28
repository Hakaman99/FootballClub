package com.example.root.finalproject.Api

import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.network.TheSportDBAPI
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {
    @Test
    fun testLastLeagueDoRequest() {
        val id = "123"
        val apiRepository = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getDataLastLeague(id)
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testNextLeagueDoRequest() {
        val id = "123"
        val apiRepository = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getDataNextLeague(id)
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testGetTeams() {
        val id = "123"
        val apiRepository = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getTeams(id)
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testLookUpTeamDoRequest() {
        val id = "123"
        val apiRepository = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getDataLookUpTeam(id)
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testLookUpEventDoRequest() {
        val id = "123"
        val apiRepository = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getDataLookUpTeam(id)
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testGetLookAllPlayer() {
        val id = "123"
        val apiRepository = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getLookAllPlayer(id)
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testGetLookUpPlayer() {
        val id = "123"
        val apiRepository = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getLookUpPlayer(id)
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}