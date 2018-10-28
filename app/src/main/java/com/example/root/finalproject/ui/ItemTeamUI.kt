package com.example.root.finalproject.ui

import android.annotation.TargetApi
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.root.finalproject.R.id.team_badge
import com.example.root.finalproject.R.id.team_name
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class ItemTeamUI : AnkoComponent<ViewGroup> {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                }
                background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                radius = dip(8).toFloat()
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = team_badge
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = team_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
        }
    }

}