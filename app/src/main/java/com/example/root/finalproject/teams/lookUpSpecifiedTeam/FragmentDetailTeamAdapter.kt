package com.example.root.finalproject.teams.lookUpSpecifiedTeam

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Overview.OverViewFragment
import com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.PlayersFragment

class FragmentDetailTeamAdapter(fragment: FragmentManager,
                                private val id: String?) : FragmentPagerAdapter(fragment) {
    companion object {
        const val OVERVIEW = "OverView"
        const val PLAYERS = "Players"
    }

    override fun getItem(p0: Int): Fragment? = when (p0) {
        0 -> OverViewFragment.newInstance(id)
        1 -> PlayersFragment.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> OVERVIEW
            1 -> PLAYERS
            else -> ""
        }

    }

    override fun getCount(): Int = 2
}