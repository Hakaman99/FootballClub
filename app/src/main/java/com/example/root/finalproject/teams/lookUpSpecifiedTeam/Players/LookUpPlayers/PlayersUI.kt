package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players.LookUpPlayers

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.*
import org.jetbrains.anko.*

class PlayersUI : AnkoComponent<Context> {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = wrapContent) {
                padding = dip(0)
                margin = dip(0)
            }
            imageView {
                id = img_player
                setImageResource(R.drawable.ic_launcher_background)
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(0)
            }

            progressBar {
                id = progress_bar_player
            }.lparams {
                centerInParent()
            }

            linearLayout {
                id = ll_info_body_size
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.START
                textView {
                    textResource = R.string.weight
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textSize = 17f
                }.lparams(width = dip(0), height = wrapContent) {
                    leftMargin = dip(5)
                    weight = 1f
                    this.gravity = Gravity.CENTER_VERTICAL

                }
                textView {
                    textResource = R.string.height
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textSize = 17f
                }.lparams(width = dip(0), height = wrapContent) {
                    weight = 1f
                    this.gravity = Gravity.CENTER_VERTICAL
                }
            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(10)
                below(img_player)
            }
            linearLayout {
                id = ll_body_size
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.START
                textView {
                    id = tv_weight_player
                    textResource = R.string.Lorem
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textSize = 25f
                }.lparams(width = dip(0), height = wrapContent) {
                    leftMargin = dip(5)
                    weight = 1f
                    this.gravity = Gravity.CENTER_VERTICAL

                }
                textView {
                    id = tv_height_player
                    textResource = R.string.Lorem
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textSize = 25f
                }.lparams(width = dip(0), height = wrapContent) {
                    weight = 1f
                    this.gravity = Gravity.CENTER_VERTICAL
                }
            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(10)
                below(ll_info_body_size)
            }
            textView {
                id = tv_forward
                textResource = R.string.forward
                textSize = 15f
            }.lparams {
                centerHorizontally()
                topMargin = dip(10)
                below(ll_body_size)
            }
            view {
                id = lines
                backgroundColor = Color.GRAY
            }.lparams(width = matchParent, height = dip(1)) {
                below(tv_forward)
                topMargin = dip(3)
                bottomMargin = dip(5)
            }
            scrollView {
                relativeLayout {
                    textView {
                        id = description_forward
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(width = matchParent, height = wrapContent)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                below(lines)
            }
        }
    }
}