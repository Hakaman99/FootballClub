package com.example.root.finalproject.match

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.root.finalproject.match.teamLastLeague.TeamLastLeagueFragment
import com.example.root.finalproject.match.teamNextLeague.TeamNextLeagueFragment

class AdapterMatchFragment(fragment: FragmentManager) : FragmentPagerAdapter(fragment) {
    companion object {
        const val LAST_MATCH = "Last Match"
        const val NEXT_MATCH = "Next Match"
    }

    override fun getItem(p0: Int): Fragment? = when (p0) {
        0 -> TeamLastLeagueFragment.newInstance()
        1 -> TeamNextLeagueFragment.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> LAST_MATCH
            1 -> NEXT_MATCH
            else -> ""
        }

    }

    override fun getCount(): Int = 2

}