package com.daniily.kodeprojectfive.view

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniily.kodeprojectfive.data.FeedBase
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager


const val VIEW_TYPE_NEWS = 0b00000001
const val VIEW_TYPE_NOTIFICATIONS = 0b00000010


class FeedViewAdapter(activity: Activity, private val items: List<FeedBase>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val delegatesManager = AdapterDelegatesManager<List<FeedBase>>()

    init {
        delegatesManager.fallbackDelegate = DefaultFallbackDelegate()
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items, position, holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addDelegate(delegate: AdapterDelegate<List<FeedBase>>) = delegatesManager.addDelegate(delegate)
    fun removeDelegate(delegate: AdapterDelegate<List<FeedBase>>) = delegatesManager.removeDelegate(delegate)

    fun addDelegate(viewType: Int, allowReplacingDelegate: Boolean = false, delegate: AdapterDelegate<List<FeedBase>>)
            = delegatesManager.addDelegate(viewType, allowReplacingDelegate, delegate)

    fun removeDelegate(viewType: Int) = delegatesManager.removeDelegate(viewType)
}