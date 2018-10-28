package com.example.root.finalproject.ui

import android.annotation.TargetApi
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.*
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ItemLeagueUI : AnkoComponent<ViewGroup> {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(5)
                bottomMargin = dip(5)
            }
            background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
            radius = dip(8).toFloat()
            imageView {
                id = alarm
                setImageResource(R.drawable.ic_add_alarm)
                scaleType = ImageView.ScaleType.FIT_CENTER
            }.lparams(width = dip(50), height = dip(30)) {
                this.gravity = Gravity.RIGHT
                topMargin = dip(10)
                marginEnd = dip(10)
            }

            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_HORIZONTAL
                // HOME TEAM
                textView {
                    id = strHomeTeam
                    textResource = R.string.Lorem
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                }.lparams(width = dip(0), height = wrapContent) {
                    weight = 1f
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                    this.gravity = Gravity.CENTER_VERTICAL

                }
                //THIS
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    textView {
                        id = tvDateLeagueUI
                        textResource = R.string.Lorem
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    }
                    textView {
                        id = score
                        textResource = R.string.Lorem
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textSize = 16f
                        textColor = ContextCompat.getColor(ctx, R.color.colorAccent)
                    }
                }.lparams(width = dip(0), height = wrapContent) {
                    weight = 1f
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                    this.gravity = Gravity.CENTER_VERTICAL
                }
                // AWAY TEAM


                textView {
                    id = strAwayTeam
                    textResource = R.string.Lorem
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                }.lparams(width = dip(0), height = wrapContent) {
                    weight = 1f
                    topMargin = dip(5)
                    bottomMargin = dip(5)
                    this.gravity = Gravity.CENTER_VERTICAL

                }

            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(20)
            }
        }

    }

}