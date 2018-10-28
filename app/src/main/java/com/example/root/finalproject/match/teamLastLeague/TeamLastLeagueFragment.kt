package com.example.root.finalproject.match.teamLastLeague

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.*
import com.example.root.finalproject.match.RecyclerViewTeamUI
import com.example.root.finalproject.match.detailMatch.DetailActivity
import com.example.root.finalproject.match.teamLastLeague.model.TeamLastLeague
import com.example.root.finalproject.match.teamLastLeague.presenter.TeamLastLeaguePresenter
import com.example.root.finalproject.match.teamLastLeague.view.TeamLastLeagueView
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.invisible
import com.example.root.finalproject.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TeamLastLeagueFragment : Fragment(), TeamLastLeagueView, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener {

    private val teamsLastMatch: MutableList<TeamLastLeague> = mutableListOf()
    private lateinit var idLeague: String
    private lateinit var presenter: TeamLastLeaguePresenter
    private lateinit var adapter: TeamLastMatchLeagueAdapter

    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spinners: Spinner

    companion object {
        fun newInstance(): TeamLastLeagueFragment = TeamLastLeagueFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        val request = ApiRepository()
        val gson = Gson()
        setAdapter(teamsLastMatch)
        presenter = TeamLastLeaguePresenter(this, request, gson)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinners.adapter = spinnerAdapter
        spinners.onItemSelectedListener = this

        swipeRefreshLayout.onRefresh {
            presenter.getTeamLastLeague(idLeague)
            hideLoading()
        }

    }

    private fun init() {
        listTeam = find(recycler_view_team)
        progressBar = find(progress_bar_rc_team)
        swipeRefreshLayout = find(swipe_refresh_layout_team)
        spinners = find(spinner)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return RecyclerViewTeamUI().createView(AnkoContext.create(requireContext()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(menu_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val editext: EditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)
            editext.hint = getString(R.string.search)
            searchView.setOnQueryTextListener(this)
        }
    }

    override fun onQueryTextSubmit(search: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(search: String): Boolean {
        if (search.isNotEmpty()) {
            val cari = search.toLowerCase()
            val data = teamsLastMatch.filter {
                it.homeTeam.toLowerCase().contains(cari) ||
                        it.awayTeam.toLowerCase().contains(cari)
            }
            setAdapter(data)
        } else {
            setAdapter(teamsLastMatch)
        }
        return true
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun getTeamLastLeagueList(data: List<TeamLastLeague>) {
        swipeRefreshLayout.isRefreshing = false
        teamsLastMatch.clear()
        teamsLastMatch.addAll(data)
        adapter.notifyDataSetChanged()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        idLeague = resources.getStringArray(R.array.id_league)[position]
        presenter.getTeamLastLeague(idLeague)
    }

    private fun setAdapter(data: List<TeamLastLeague>) {
        adapter = TeamLastMatchLeagueAdapter(data) {
            startActivity<DetailActivity>(Constant.TAG_DETAIL to it.idEvent)
        }
        listTeam.adapter = adapter
    }
}

