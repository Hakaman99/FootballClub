package com.example.root.finalproject.teams.lookUpSpecifiedTeam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.add_to_favorite
import com.example.root.finalproject.db.databaseTeams
import com.example.root.finalproject.favorite.model.FavoriteTeam
import com.example.root.finalproject.model.lookUpTeam.LookUpTeam
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Presenter.LookUpTeamDetailPresenter
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.View.LookUpTeamDetailView
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.invisible
import com.example.root.finalproject.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), LookUpTeamDetailView {
    private lateinit var idTeam: String
    private lateinit var presenter: LookUpTeamDetailPresenter
    private val teams: MutableList<LookUpTeam> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        setSupportActionBar(toolbarDetail)
        supportActionBar?.title = resources.getString(R.string.titleDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        idTeam = intent.getStringExtra(Constant.TAG_DETAIL)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = LookUpTeamDetailPresenter(this, request, gson)
        presenter.lookUpTeam(idTeam)
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

    private fun removeFromFavorite() {
        try {
            databaseTeams.use {
                delete(FavoriteTeam.TABLE_FAVORITE, "(${FavoriteTeam.TEAM_ID} = {${FavoriteTeam.id}})",
                        "${FavoriteTeam.id}" to idTeam)
            }
            toast(getString(R.string.removed))
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun addToFavorite() {
        try {
            databaseTeams.use {
                insert(FavoriteTeam.TABLE_FAVORITE,
                        FavoriteTeam.TEAM_ID to idTeam,
                        FavoriteTeam.TEAM_NAME to teams[0].nameTeam,
                        FavoriteTeam.TEAM_BADGE to teams[0].teamBadge)
            }
            toast(getString(R.string.added))
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun favoriteState() {
        databaseTeams.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
                    .whereArgs("(${FavoriteTeam.TEAM_ID} = {${FavoriteTeam.id}})",
                            "${FavoriteTeam.id}" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun hideLoading() {
        progress_bar_detail_activity.invisible()
    }

    override fun showLoading() {
        progress_bar_detail_activity.visible()
    }

    override fun geLookUpTeam(data: List<LookUpTeam>) {
        teams.clear()
        teams.addAll(data)
        val adapter = FragmentDetailTeamAdapter(supportFragmentManager, data[0].description)
        viewPagerDetail.adapter = adapter
        tabLayoutDetail.setupWithViewPager(viewPagerDetail)
        Picasso.get().load(data[0].teamBadge).into(img_club)
        tv_name_club.text = data[0].nameTeam
        tv_years_club.text = data[0].formedYear.toString()
        tv_stadium_club.text = data[0].stadium
    }


}