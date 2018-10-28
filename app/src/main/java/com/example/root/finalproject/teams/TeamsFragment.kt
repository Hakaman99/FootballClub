package com.example.root.finalproject.teams

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.example.root.finalproject.R
import com.example.root.finalproject.R.array.league
import com.example.root.finalproject.R.id.list_event
import com.example.root.finalproject.R.id.menu_search
import com.example.root.finalproject.network.ApiRepository
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.DetailTeamActivity
import com.example.root.finalproject.teams.model.Team
import com.example.root.finalproject.teams.presenter.TeamsPresenter
import com.example.root.finalproject.teams.view.TeamsView
import com.example.root.finalproject.utils.Constant
import com.example.root.finalproject.utils.invisible
import com.example.root.finalproject.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.titleResource
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : Fragment(), TeamsView, AnkoComponent<Context>, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener {
    private val teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var spinner: Spinner
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueName: String

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        coordinatorLayout {
            lparams(width = matchParent, height = matchParent) {
                backgroundColor = ContextCompat.getColor(context, R.color.colorBackgroundRV)
            }
            themedAppBarLayout(R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                lparams(width = matchParent, height = wrapContent)
                toolbar {
                    popupTheme = R.style.ThemeOverlay_AppCompat_Dark_ActionBar
                    titleResource = R.string.teams
                    setTitleTextColor(Color.WHITE)
                }.lparams(width = matchParent, height = wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }.let { (activity as AppCompatActivity).setSupportActionBar(it) }
            }.lparams(width = matchParent, height = wrapContent)
            frameLayout {
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    cardView {
                        spinner = spinner {
                        }.lparams(width = matchParent, height = wrapContent) { bottomMargin = dip(5) }
                    }
                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(R.color.colorAccent,
                                android.R.color.holo_green_light,
                                android.R.color.holo_orange_light,
                                android.R.color.holo_red_light)

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)
                            listEvent = recyclerView {
                                id = list_event
                                lparams(width = matchParent, height = wrapContent)
                                layoutManager = LinearLayoutManager(ctx)
                            }
                            progressBar = progressBar {
                            }.lparams {
                                centerHorizontally()
                            }
                        }
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    topPadding = dip(16)
                    leftPadding = dip(16)
                    rightPadding = dip(16)
                }
            }.lparams { behavior = AppBarLayout.ScrollingViewBehavior() }


        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
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
            editext.textColor = Color.WHITE
            searchView.setOnQueryTextListener(this)
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(search: String): Boolean {
        if (search.isNotEmpty()) {
            val cari = search.toLowerCase()
            val data = teams.filter {
                it.teamName.toLowerCase().contains(cari)
            }
            setAdapter(data)
        } else {
            setAdapter(teams)
        }
        return true
    }

    private fun setAdapter(data: List<Team>) {
        adapter = TeamsAdapter(data) {
            startActivity<DetailTeamActivity>(Constant.TAG_DETAIL to it.teamId)
        }
        listEvent.adapter = adapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        setAdapter(teams)
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
        spinner.onItemSelectedListener = this

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
            hideLoading()
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        leagueName = spinner.selectedItem.toString()
        val data = leagueName.split(' ')
        leagueName = data.joinToString("%20")
        presenter.getTeamList(leagueName)
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}