package com.example.root.finalproject.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.root.finalproject.favorite.matches.MatchesFavoriteFragment
import com.example.root.finalproject.favorite.teams.TeamsFavoriteFragment

class AdapterFavoriteFragment(fragment: FragmentManager) : FragmentPagerAdapter(fragment) {
    companion object {
        const val MATCHES = "Matches"
        const val TEAMS = "Teams"
    }

    override fun getItem(p0: Int): Fragment? = when (p0) {
        0 -> MatchesFavoriteFragment.newInstance()
        1 -> TeamsFavoriteFragment.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> MATCHES
            1 -> TEAMS
            else -> ""
        }

    }

    override fun getCount(): Int = 2
}