package com.example.root.finalproject.match.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.root.finalproject.R
import com.example.root.finalproject.R.drawable.ic_add_to_favorites
import com.example.root.finalproject.R.drawable.ic_added_to_favorites
import com.example.root.finalproject.R.id.*
import com.example.root.finalproject.db.databaseMatch
import com.example.root.finalproject.favorite.model.FavoriteMatch
import com.example.root.finalproject.match.DetailUI
import com.example.root.finalproject.match.detailMatch.presenter.LookUpPresenter
import com.example.root.finalproject.match.detailMatch.view.LookUpView
import com.example.root.finalproject.model.lookUpEvent.LookUpEvent
import com.example.root.finalproject.model.lookUpTeam.LookUpTeam
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.Time
import com.example.root.finalproject.utils.invisible
import com.example.root.finalproject.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), LookUpView {
    private lateinit var idEvent: String
    private val teams: MutableList<LookUpEvent> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var progresBar: ProgressBar
    private lateinit var presenter: LookUpPresenter
    private lateinit var dateEventsDetail: TextView
    private lateinit var imageHomeTeam: ImageView
    private lateinit var imageAwayTeam: ImageView
    private lateinit var nameHomeTeam: TextView
    private lateinit var nameAwayTeam: TextView
    private lateinit var scoreFootBall: TextView
    private lateinit var homeGoalDetail: TextView
    private lateinit var awayGoalDetail: TextView
    private lateinit var homeShots: TextView
    private lateinit var awayShots: TextView
    private lateinit var homeLineupGoalkeeper: TextView
    private lateinit var awayLineupGoalkeeper: TextView
    private lateinit var homeLineupDefense: TextView
    private lateinit var awayLineupDefense: TextView
    private lateinit var homeLineupMidfield: TextView
    private lateinit var awayLineupMidfield: TextView
    private lateinit var homeLineupForward: TextView
    private lateinit var awayLineupForward: TextView
    private lateinit var homeLineupSubstitutes: TextView
    private lateinit var awayLineupSubstitutes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppThemeActionBar)
        DetailUI().setContentView(this)
        supportActionBar?.title = resources.getString(R.string.titleDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progresBar = find(progress_bar_detail)

        val intent = intent
        idEvent = intent.getStringExtra(Constant.TAG_DETAIL)

        favoriteState()
        val gson = Gson()
        val request = ApiRepository()
        presenter = LookUpPresenter(this, request, gson)
        presenter.getLookUpEvent(idEvent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            databaseMatch.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.ID_EVENT to idEvent,
                        FavoriteMatch.DATE_EVENT to teams[0].dateEvent,
                        FavoriteMatch.TIME to teams[0].time,
                        FavoriteMatch.HOME_SCORE to teams[0].homeScore,
                        FavoriteMatch.AWAY_SCORE to teams[0].awayScore,
                        FavoriteMatch.HOME_TEAM to teams[0].homeTeam,
                        FavoriteMatch.AWAY_TEAM to teams[0].awayTeam)
            }
            toast(getString(R.string.added))
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            databaseMatch.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(${FavoriteMatch.ID_EVENT} = {${FavoriteMatch.ID_DB}})",
                        "${FavoriteMatch.ID_DB}" to idEvent)

            }
            toast(getString(R.string.removed))
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState() {
        databaseMatch.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(${FavoriteMatch.ID_EVENT} = {${FavoriteMatch.id}})",
                            "${FavoriteMatch.id}" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progresBar.visible()
    }

    override fun hideLoading() {
        progresBar.invisible()
    }

    override fun getLookUpEventList(data: List<LookUpEvent>) {
        teams.clear()
        teams.addAll(data)
        scoreAndDate(data[0].dateEvent, data[0].time, data[0].homeScore, data[0].awayScore)
        nameTeam(data[0].homeTeam, data[0].awayTeam)
        goalDetail(data[0].homeGoalDetails, data[0].awayGoalDetails)
        shoots(data[0].homeShots, data[0].awayShots)
        lineUpGoalKeeper(data[0].homeLineupGoalkeeper, data[0].awayLineupGoalkeeper)
        lineupDefense(data[0].homeLineupDefense, data[0].awayLineupDefense)
        lineUpMidfield(data[0].homeLineupMidfield, data[0].awayLineupMidfield)
        lineUpForward(data[0].homeLineupForward, data[0].awayLineupForward)
        lineUpSubstitutes(data[0].homeLineupSubstitutes, data[0].awayLineupSubstitutes)


        presenter.getLookUpTeam(data[0].idhomeTeam.toString(), data[0].idAwayTeam.toString())
    }

    override fun geLookUpTeamList(dataHome: List<LookUpTeam>, dataAway: List<LookUpTeam>) {
        image(dataHome[0].teamBadge, dataAway[0].teamBadge)
    }

    private fun image(imgHome: String? = null, imgAway: String? = null) {
        imageHomeTeam = find(image_home_detail)
        imageAwayTeam = find(image_away_detail)
        Picasso.get().load(imgHome).into(imageHomeTeam)
        Picasso.get().load(imgAway).into(imageAwayTeam)
    }

    private fun nameTeam(nameHomeTeam: String? = null, nameAwayTeam: String? = null) {
        this.nameHomeTeam = find(tv_image_home_detail)
        this.nameAwayTeam = find(tv_image_away_detail)

        this.nameHomeTeam.text = nameHomeTeam ?: ""
        this.nameAwayTeam.text = nameAwayTeam ?: ""
    }

    private fun scoreAndDate(date: String, time: String, scoreHome: Int? = null, scoreAway: Int? = null) {
        dateEventsDetail = find(date_events_detail)
        scoreFootBall = find(score_detail)
        val date = Time.formatUTCtoGMT(date, time.substring(0, 5), Constant.DATE)
        dateEventsDetail.text = date
        scoreFootBall.text = "${scoreHome?.toString() ?: ""} VS ${scoreAway?.toString() ?: ""}"
    }

    private fun goalDetail(homeGoal: String? = null, awayGoal: String? = null) {
        homeGoalDetail = find(home_goal_details)
        awayGoalDetail = find(away_goal_details)

        homeGoalDetail.text = homeGoal ?: ""
        awayGoalDetail.text = awayGoal ?: ""
    }

    private fun shoots(homeShot: Int? = null, awayShot: Int? = null) {
        homeShots = find(home_shots_detail)
        awayShots = find(away_shots_detail)

        homeShots.text = homeShot?.toString() ?: ""
        awayShots.text = awayShot?.toString() ?: ""
    }

    private fun lineUpGoalKeeper(homeGoalKeeper: String? = null, awayGoalKeeper: String? = null) {
        homeLineupGoalkeeper = find(home_line_up_goal_keeper)
        awayLineupGoalkeeper = find(away_line_up_goal_keeper)

        homeLineupGoalkeeper.text = homeGoalKeeper
        awayLineupGoalkeeper.text = awayGoalKeeper
    }

    private fun lineUpSubstitutes(homeLineupSubs: String? = null, awayLineupSubs: String? = null) {
        homeLineupSubstitutes = find(home_lineup_substitutes)
        awayLineupSubstitutes = find(away_lineup_substitutes)

        homeLineupSubstitutes.text = homeLineupSubs
        awayLineupSubstitutes.text = awayLineupSubs
    }

    private fun lineUpForward(homeLineUpForward: String? = null, awaylineUpForward: String? = null) {
        homeLineupForward = find(home_lineup_forward)
        awayLineupForward = find(away_lineup_forward)

        homeLineupForward.text = homeLineUpForward
        awayLineupForward.text = awaylineUpForward
    }

    private fun lineUpMidfield(HomeMidfield: String? = null, awayMidfield: String? = null) {
        homeLineupMidfield = find(home_line_up_midfield)
        awayLineupMidfield = find(away_lineup_midfield)

        homeLineupMidfield.text = HomeMidfield
        awayLineupMidfield.text = awayMidfield
    }

    private fun lineupDefense(homeDefense: String? = null, awayDefense: String? = null) {
        homeLineupDefense = find(home_line_up_defense)
        awayLineupDefense = find(away_line_up_defense)

        homeLineupDefense.text = homeDefense
        awayLineupDefense.text = awayDefense

    }


}