package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Model

import com.google.gson.annotations.SerializedName

data class LookAllPlayer(
        @SerializedName("idPlayer")
        val idPlayer: String? = null,
        @SerializedName("strPlayer")
        val namePlayer: String? = null,
        @SerializedName("strPosition")
        val positionPlayer: String? = null,
        @SerializedName("strCutout")
        val imagePLayer: String? = null
)