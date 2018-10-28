package com.example.root.finalproject.favorite.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.root.finalproject.R.id.rv_favorite
import com.example.root.finalproject.R.id.swipe_refresh_layout_favorite
import com.example.root.finalproject.db.databaseMatch
import com.example.root.finalproject.favorite.model.FavoriteMatch
import com.example.root.finalproject.match.detailMatch.DetailActivity
import com.example.root.finalproject.utils.Constant
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class MatchesFavoriteFragment : Fragment() {
    private val favorites: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    companion object {
        fun newInstance(): MatchesFavoriteFragment = MatchesFavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return RecyclerViewFavoriteMatch().createView(AnkoContext.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        adapter = FavoriteMatchAdapter(favorites) {
            startActivity<DetailActivity>(Constant.TAG_DETAIL to "${it.eventId}")
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
        context?.databaseMatch?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}