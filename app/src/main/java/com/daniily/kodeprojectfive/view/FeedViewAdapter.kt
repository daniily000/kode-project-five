package com.daniily.kodeprojectfive.view

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniily.kodeprojectfive.data.FeedBase
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class FeedViewAdapter(activity: Activity, private val items: List<FeedBase>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val delegatesManager = AdapterDelegatesManager<List<FeedBase>>()

    init {
        delegatesManager.addDelegate(NewsViewAdapterDelegate(activity))
        delegatesManager.addDelegate(NotificationViewAdapterDelegate(activity))
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
}