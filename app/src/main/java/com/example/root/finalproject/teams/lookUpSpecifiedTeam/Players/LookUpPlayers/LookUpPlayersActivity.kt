package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.*
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Model.LookUpPlayer
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.Presenter.LookUpPlayerPresenter
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.View.LookUpPlayerView
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.invisible
import com.example.root.finalproject.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class LookUpPlayersActivity : AppCompatActivity(), LookUpPlayerView {
    private lateinit var idPlayers: String
    private val player: MutableList<LookUpPlayer> = mutableListOf()
    private lateinit var presenter: LookUpPlayerPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var imgPlayer: ImageView
    private lateinit var weight: TextView
    private lateinit var height: TextView
    private lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppThemeActionBar)
        PlayersUI().setContentView(this)
        init()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        idPlayers = intent.getStringExtra(Constant.TAG_PLAYERS)
        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = LookUpPlayerPresenter(this, apiRepository, gson)
        presenter.lookUpPlayer(idPlayers)
    }

    private fun init() {
        progressBar = find(progress_bar_player)
        imgPlayer = find(img_player)
        weight = find(tv_weight_player)
        height = find(tv_height_player)
        description = find(description_forward)
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun getLookUpPlayer(data: List<LookUpPlayer>) {
        player.clear()
        player.addAll(data)
        supportActionBar?.title = data[0].name
        Picasso.get().load(data[0].fanart).into(imgPlayer)
        weight.text = data[0].weight
        height.text = data[0].height
        description.text = data[0].description
    }
}