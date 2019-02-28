package com.driimovies.app.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

class ViewBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("visibleOrGone")
        fun setVisibleOrGone(view: View, isVisible: Boolean) {
            if (isVisible) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }

        @JvmStatic
        @BindingAdapter("visibleOrInvisible")
        fun setVisibleOrInvisible(view: View, isVisible: Boolean) {
            if (isVisible) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.INVISIBLE
            }
        }
    }
}