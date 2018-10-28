package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.root.finalproject.R.id.*
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers.LookUpPlayersActivity
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Model.LookAllPlayer
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.Presenter.LookAllPlayerPresenter
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.View.LookAllPlayerView
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.invisible
import com.example.root.finalproject.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class PlayersFragment : Fragment(), LookAllPlayerView {
    private var idTeam: String? = null
    private lateinit var presenter: LookAllPlayerPresenter
    private val player: MutableList<LookAllPlayer> = mutableListOf()
    private lateinit var adapter: PlayersAdapter
    private lateinit var listPlayer: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    companion object {
        fun newInstance(): PlayersFragment = PlayersFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return RecyclerViewPlayersUI().createView(AnkoContext.create(requireContext()))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        val intent = activity?.intent
        idTeam = intent?.getStringExtra(Constant.TAG_DETAIL)
        val gson = Gson()
        val apiRepository = ApiRepository()
        setAdapter(player)
        presenter = LookAllPlayerPresenter(this, apiRepository, gson)
        presenter.getLookAllPlayer(idTeam)
        swipeRefreshLayout.onRefresh {
            presenter.getLookAllPlayer(idTeam)
            hideLoading()
        }
    }

    private fun init() {
        listPlayer = find(rv_players)
        swipeRefreshLayout = find(swipe_refresh_layout_players)
        progressBar = find(progress_bar_rc_players)
    }

    private fun setAdapter(data: List<LookAllPlayer>) {
        adapter = PlayersAdapter(data) {
            startActivity<LookUpPlayersActivity>(Constant.TAG_PLAYERS to it.idPlayer)
        }

        listPlayer.adapter = adapter
    }


    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun getAllPlayer(data: List<LookAllPlayer>) {
        swipeRefreshLayout.isRefreshing = false
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()
    }
}