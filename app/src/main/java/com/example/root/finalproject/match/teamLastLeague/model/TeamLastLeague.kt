package com.example.root.finalproject.match.teamLastLeague.model

import com.google.gson.annotations.SerializedName

data class TeamLastLeague(
        @SerializedName("idEvent")
        val idEvent: String? = null,
        @SerializedName("dateEvent")
        val dateEvent: String,
        @SerializedName("strTime")
        val time: String,
        @SerializedName("strHomeTeam")
        val homeTeam: String,
        @SerializedName("strAwayTeam")
        val awayTeam: String,
        @SerializedName("intHomeScore")
        val homeScore: Int? = null,
        @SerializedName("intAwayScore")
        val awayScore: Int? = null,
        @SerializedName("strLeague")
        val league: String? = null)
