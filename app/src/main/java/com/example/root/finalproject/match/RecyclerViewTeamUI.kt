package com.example.root.finalproject.match

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.*
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class RecyclerViewTeamUI : AnkoComponent<Context> {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent) {
                backgroundColor = ContextCompat.getColor(context, R.color.colorBackgroundRV)
            }
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            cardView {
                spinner {
                    id = spinner
                }
            }.lparams(width = matchParent, height = wrapContent) { bottomMargin = dip(5) }
            swipeRefreshLayout {
                id = swipe_refresh_layout_team
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    recyclerView {
                        id = recycler_view_team
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                    progressBar {
                        id = progress_bar_rc_team
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }

        }
    }

}
