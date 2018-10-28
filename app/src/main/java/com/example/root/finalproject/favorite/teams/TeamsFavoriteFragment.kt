package com.example.root.finalproject.favorite.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.root.finalproject.R.id.rv_favorite
import com.example.root.finalproject.R.id.swipe_refresh_layout_favorite
import com.example.root.finalproject.db.databaseTeams
import com.example.root.finalproject.favorite.model.FavoriteTeam
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.DetailTeamActivity
import com.example.root.finalproject.utils.Constant
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TeamsFavoriteFragment : Fragment() {
    private val favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    companion object {
        fun newInstance(): TeamsFavoriteFragment = TeamsFavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return RecyclerViewFavoriteTeams().createView(AnkoContext.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        adapter = FavoriteTeamsAdapter(favorites) {
            startActivity<DetailTeamActivity>(Constant.TAG_DETAIL to "${it.teamId}")
        }

        listEvent.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun init() {
        listEvent = find(rv_favorite)
        swipeRefresh = find(swipe_refresh_layout_favorite)
    }

    private fun showFavorite() {
        context?.databaseTeams?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}