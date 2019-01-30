package com.daniily.kodeprojectfive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniily.kodeprojectfive.data.News
import com.daniily.kodeprojectfive.data.Notification
import com.daniily.kodeprojectfive.view.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_feed.*

const val LOGOUT = 0x00000000
const val NO_LOGOUT = 0x00000001

class FeedActivity : AppCompatActivity() {

    private lateinit var newsDelegate: NewsViewAdapterDelegate
    private lateinit var notificationDelegate: NotificationViewAdapterDelegate

    private val feedItems = listOf(

        News(
            "Looking Glass",
            "Marcus entered the bright room of secured high-tech state-of-the-art skyscraper and finally got " +
                    "the desired laptop of !Nvite's CEO. ",
            R.mipmap.image1
        ),

        Notification(
            "Marcus",
            "I'm in. Trying to figure out who boosted our numbers. Whoa! Whoa! What happened?",
            R.mipmap.image_marcus
        ),

        News(
            "Suddenly...",
            "The laptop started glitching, ignoring any Marcus' attempt to fix anything.",
            R.mipmap.image2
        ),

        Notification(
            "Sitara",
            "The flagged clients just vanished! We lost the bots. What did you do?",
            R.mipmap.image_sitara
        ),

        Notification(
            "Marcus",
            "Nothing! This isn't me, I didn't...",
            R.mipmap.image_marcus
        )
    )


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_all -> {
                content_recycler_view.adapter = FeedViewAdapter(this, feedItems).apply {
                    addDelegate(newsDelegate)
                    addDelegate(notificationDelegate)
                }
                Picasso.get().load(R.mipmap.image_head1).into(image_head)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news -> {
                content_recycler_view.adapter = FeedViewAdapter(this, feedItems).apply {
                    addDelegate(newsDelegate)
                }
                Picasso.get().load(R.mipmap.image_head2).into(image_head)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                content_recycler_view.adapter = FeedViewAdapter(this, feedItems).apply {
                    addDelegate(notificationDelegate)
                }
                Picasso.get().load(R.mipmap.image_head3).into(image_head)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        setResult(NO_LOGOUT)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        logout.setOnClickListener {
            setResult(LOGOUT)
            finish()
        }

        newsDelegate = NewsViewAdapterDelegate(this)
        notificationDelegate = NotificationViewAdapterDelegate(this)

        content_recycler_view.adapter = FeedViewAdapter(this, feedItems).apply {
            addDelegate(VIEW_TYPE_NEWS, true, newsDelegate)
            addDelegate(VIEW_TYPE_NOTIFICATIONS, false, notificationDelegate)
        }

        content_recycler_view.layoutManager = LinearLayoutManager(this)
    }


}
