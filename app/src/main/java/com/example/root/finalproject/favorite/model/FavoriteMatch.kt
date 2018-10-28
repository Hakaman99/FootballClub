package com.example.root.finalproject.favorite.model

data class FavoriteMatch(
        val id: Long?,
        val eventId: String?,
        val dateEvent: String,
        val time: String,
        val homeScore: String?,
        val awayScore: String?,
        val homeTeam: String?,
        val awayTeam: String?) {
    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val id: String = "id"
        const val ID_EVENT: String = "ID_EVENT"
        const val TIME: String = "TIME"
        const val ID_DB: String = "id"
        const val DATE_EVENT = "DATE_EVENT"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
    }
}