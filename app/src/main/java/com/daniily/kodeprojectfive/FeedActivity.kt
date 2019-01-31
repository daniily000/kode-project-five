package com.daniily.kodeprojectfive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniily.kodeprojectfive.dao.AppDAO
import com.daniily.kodeprojectfive.dao.AppPrefsDAO
import com.daniily.kodeprojectfive.data.FeedBase
import com.daniily.kodeprojectfive.view.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_feed.*

const val LOGOUT = 0x00000000
const val NO_LOGOUT = 0x00000001

class FeedActivity : AppCompatActivity() {

    private lateinit var newsDelegate: NewsViewAdapterDelegate
    private lateinit var notificationDelegate: NotificationViewAdapterDelegate

    private lateinit var appDAO: AppDAO
    private lateinit var feedItems: List<FeedBase>

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

        appDAO = AppPrefsDAO(this)
        feedItems = appDAO.getFeedObjects()

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
