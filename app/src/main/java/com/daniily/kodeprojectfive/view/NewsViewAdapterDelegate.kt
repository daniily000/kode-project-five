package com.daniily.kodeprojectfive.view

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniily.kodeprojectfive.R
import com.daniily.kodeprojectfive.data.FeedBase
import com.daniily.kodeprojectfive.data.News
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.view_news.view.*

//
class NewsViewAdapterDelegate(activity: Activity) : AdapterDelegate<List<FeedBase>>() {

    private val inflater: LayoutInflater = activity.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder{
        Log.i("NewsViewAdapterDelegate", "INFLATING")
        return NewsViewHolder(
            inflater.inflate(
                R.layout.view_news,
                parent,
                false
            )
        )
    }

    override fun isForViewType(items: List<FeedBase>, position: Int) =
        items[position] is News

    override fun onBindViewHolder(
        items: List<FeedBase>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val o = items[position]

        Log.i("NewsViewAdapterDelegate", "BINGING:")
        Log.i("NewsViewAdapterDelegate", "    item: $o")
        Log.i("NewsViewAdapterDelegate", "    position: $position")

        if (o is News) holder.itemView.apply {
            Log.i("NewsViewAdapterDelegate", "    o IS News, binding is OK")
            image_news.setImageResource(o.imageId)
            title_news.text = o.title
            content_news.text = o.content
        } else {
            Log.e("NewsViewAdapterDelegate", "    o IS NOT News, o is $o!")
        }
    }


    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)

}