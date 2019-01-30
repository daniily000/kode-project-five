package com.daniily.kodeprojectfive.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniily.kodeprojectfive.data.FeedBase
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class DefaultFallbackDelegate : AdapterDelegate<List<FeedBase>>(){
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return EmptyHolder(parent.context)
    }

    override fun isForViewType(items: List<FeedBase>, position: Int): Boolean {
        return false
    }

    override fun onBindViewHolder(
        items: List<FeedBase>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {}

    class EmptyHolder(context: Context) : RecyclerView.ViewHolder(View(context))
}
