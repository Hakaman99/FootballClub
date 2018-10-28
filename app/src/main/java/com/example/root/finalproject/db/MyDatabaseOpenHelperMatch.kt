package com.example.root.finalproject.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.root.finalproject.favorite.model.FavoriteMatch
import org.jetbrains.anko.db.*


class MyDatabaseOpenHelperMatch(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeamMatch.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelperMatch? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelperMatch {
            if (instance == null) {
                instance = MyDatabaseOpenHelperMatch(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelperMatch
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.ID_EVENT to TEXT + UNIQUE,
                FavoriteMatch.DATE_EVENT to TEXT,
                FavoriteMatch.TIME to TEXT,
                FavoriteMatch.HOME_SCORE to TEXT,
                FavoriteMatch.AWAY_SCORE to TEXT,
                FavoriteMatch.HOME_TEAM to TEXT,
                FavoriteMatch.AWAY_TEAM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

val Context.databaseMatch: MyDatabaseOpenHelperMatch
    get() = MyDatabaseOpenHelperMatch.getInstance(applicationContext)
