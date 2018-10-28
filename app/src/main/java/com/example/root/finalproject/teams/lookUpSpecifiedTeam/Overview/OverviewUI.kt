package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Overview

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.View
import com.example.root.finalproject.R.id.tv_overview
import org.jetbrains.anko.*

class OverviewUI : AnkoComponent<Context> {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        scrollView {
            relativeLayout {
                padding = dip(15)
                textView {
                    id = tv_overview
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams(width = matchParent, height = wrapContent) {
                    centerHorizontally()
                }
            }
        }
    }

}