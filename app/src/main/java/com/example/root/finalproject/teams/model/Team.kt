package com.example.root.finalproject.teams.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        val teamId: String? = null,

        @SerializedName("strTeam")
        val teamName: String,

        @SerializedName("strTeamBadge")
        val teamBadge: String? = null,

        @SerializedName("intFormedYear")
        val teamFormedYear: String? = null,

        @SerializedName("strStadium")
        val teamStadium: String? = null,

        @SerializedName("strDescriptionEN")
        val teamDescription: String? = null
)