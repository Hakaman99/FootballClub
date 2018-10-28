package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.root.finalproject.R.id.tv_overview
import com.example.root.finalproject.utils.Constant
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find

class OverViewFragment : Fragment() {
    private lateinit var idTeam: String
    private lateinit var tvDescription: TextView

    companion object {
        fun newInstance(id: String?): OverViewFragment {
            val bind = Bundle()
            bind.putString(Constant.TAG_OVERVIEW, id)
            val overViewFragment = OverViewFragment()
            overViewFragment.arguments = bind
            return overViewFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return OverviewUI().createView(AnkoContext.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bindData = arguments
        idTeam = bindData?.getString(Constant.TAG_OVERVIEW) ?: ""
        init()
        tvDescription.text = idTeam

    }

    private fun init() {
        tvDescription = find(tv_overview)
    }
}