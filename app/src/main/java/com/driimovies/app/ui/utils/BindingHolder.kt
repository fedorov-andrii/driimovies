package com.driimovies.app.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingHolder<VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        @JvmStatic
        fun <VB : ViewDataBinding> newInstance(
            @LayoutRes layoutId: Int,
            inflater: LayoutInflater,
            parent: ViewGroup?,
            attachToParent: Boolean
        ): BindingHolder<VB> {
            val vb = DataBindingUtil.inflate<VB>(inflater, layoutId, parent, attachToParent)
            return BindingHolder(vb)
        }
    }
}
