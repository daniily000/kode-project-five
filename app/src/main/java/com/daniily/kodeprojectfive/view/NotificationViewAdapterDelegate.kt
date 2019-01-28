package com.daniily.kodeprojectfive.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniily.kodeprojectfive.R
import com.daniily.kodeprojectfive.data.FeedBase
import com.daniily.kodeprojectfive.data.Notification
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_notification.view.*

//
class NotificationViewAdapterDelegate(activity: Activity) : AdapterDelegate<List<FeedBase>>() {

    private val inflater: LayoutInflater = activity.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder{
        return NotificationViewHolder(
            inflater.inflate(
                R.layout.view_notification,
                parent,
                false
            )
        )
    }

    override fun isForViewType(items: List<FeedBase>, position: Int) =
        items[position] is Notification

    override fun onBindViewHolder(
        items: List<FeedBase>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val o = items[position]
        if (o is Notification) holder.itemView.apply {
            Picasso.get().load(o.imageId).into(image_notification)
            title_notification.text = o.title
            content_notification.text = o.message
        }
    }


    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view)

}