package com.example.root.finalproject.match.teamNextLeague

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
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
import com.example.root.finalproject.match.teamNextLeague.model.TeamNextLeague
import com.example.root.finalproject.match.teamNextLeague.presenter.TeamNextLeaguePresenter
import com.example.root.finalproject.match.teamNextLeague.view.TeamNextLeagueView
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.Time
import com.example.root.finalproject.utils.invisible
import com.example.root.finalproject.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TeamNextLeagueFragment : Fragment(), TeamNextLeagueView, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener {
    private val teamNextMatch: MutableList<TeamNextLeague> = mutableListOf()
    private lateinit var idLeague: String
    private lateinit var presenter: TeamNextLeaguePresenter
    private lateinit var adapter: TeamsNextMatchLeagueAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spinners: Spinner


    companion object {
        fun newInstance(): TeamNextLeagueFragment = TeamNextLeagueFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return RecyclerViewTeamUI().createView(AnkoContext.create(requireContext()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamNextLeaguePresenter(this, request, gson)
        setAdapter(teamNextMatch)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinners.adapter = spinnerAdapter
        spinners.onItemSelectedListener = this

        swipeRefreshLayout.onRefresh {
            presenter.getTeamsNextLeague(idLeague)
            hideLoading()
        }
    }

    private fun init() {
        listTeam = find(recycler_view_team)
        progressBar = find(progress_bar_rc_team)
        swipeRefreshLayout = find(swipe_refresh_layout_team)
        spinners = find(spinner)

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
            val data = teamNextMatch.filter {
                it.homeTeam.toLowerCase().contains(cari) ||
                        it.awayTeam.toLowerCase().contains(cari)
            }
            setAdapter(data)
        } else {
            setAdapter(teamNextMatch)
        }
        return true
    }

    private fun setAdapter(data: List<TeamNextLeague>) {
        adapter = TeamsNextMatchLeagueAdapter(data, {
            startActivity<DetailActivity>(Constant.TAG_DETAIL to it.idEvent)
        }, {
            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = getString(R.string.intent_type_calendar)
            val time = Time.formatUTCtoGMT(it.dateEvent, it.time.substring(0, 5), Constant.DATE_TIME_FORMAT_UTC)
            val timestamp = Time.timeStamp(time)
            val title = "${it.homeTeam} VS ${it.awayTeam}"

            intent.putExtra(CalendarContract.Events.TITLE, title)
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timestamp)
            intent.putExtra(CalendarContract.CalendarAlerts.ALARM_TIME, timestamp)
            startActivity(intent)
        })
        listTeam.adapter = adapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        idLeague = resources.getStringArray(R.array.id_league)[position]
        presenter.getTeamsNextLeague(idLeague)
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun getTeamNextLeagueList(data: List<TeamNextLeague>) {
        swipeRefreshLayout.isRefreshing = false
        teamNextMatch.clear()
        teamNextMatch.addAll(data)
        adapter.notifyDataSetChanged()
    }


}