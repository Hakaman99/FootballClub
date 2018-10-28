package com.example.root.finalproject.model.lookUpTeam

import com.google.gson.annotations.SerializedName

data class LookUpTeam(
        @SerializedName("strTeamBadge")
        val teamBadge: String? = null,
        @SerializedName("strTeam")
        val nameTeam: String? = null,
        @SerializedName("intFormedYear")
        val formedYear: Int? = null,
        @SerializedName("strStadium")
        val stadium: String? = null,
        @SerializedName("strDescriptionEN")
        val description: String? = null
)
