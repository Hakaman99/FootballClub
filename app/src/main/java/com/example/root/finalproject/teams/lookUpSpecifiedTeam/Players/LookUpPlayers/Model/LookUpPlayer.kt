package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Model

import com.google.gson.annotations.SerializedName

data class LookUpPlayer(
        @SerializedName("strPlayer")
        val name: String? = null,
        @SerializedName("strFanart1")
        val fanart: String? = null,
        @SerializedName("strHeight")
        val height: String? = null,
        @SerializedName("strWeight")
        val weight: String? = null,
        @SerializedName("strDescriptionEN")
        val description: String? = null
)