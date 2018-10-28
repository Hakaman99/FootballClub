package com.example.root.finalproject.teams.lookUpSpecifiedTeam.Players

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

class ItemPlayersUI : AnkoComponent<ViewGroup> {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(5)
                bottomMargin = dip(5)
            }

            background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
            radius = dip(8).toFloat()
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.START
                imageView {
                    id = img_player
                    setImageResource(R.drawable.ic_launcher_background)
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = dip(0), height = dip(50)) {
                    weight = 1f
                    this.gravity = Gravity.CENTER_VERTICAL
                }
                textView {
                    id = tv_name_player
                    textResource = R.string.Lorem
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                }.lparams(width = dip(0), height = wrapContent) {
                    leftMargin = dip(5)
                    weight = 1f
                    this.gravity = Gravity.CENTER_VERTICAL

                }
                textView {
                    id = tv_position_player
                    textResource = R.string.Lorem
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                }.lparams(width = dip(0), height = wrapContent) {
                    weight = 1f
                    this.gravity = Gravity.CENTER_VERTICAL
                }
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
            }
        }
    }
}