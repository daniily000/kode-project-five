package com.daniily.kodeprojectfive.view

import android.view.View
import com.daniily.kodeprojectfive.R
import com.daniily.kodeprojectfive.data.FeedBase
import com.daniily.kodeprojectfive.data.News
import com.daniily.kodeprojectfive.data.Notification
import kotlinx.android.synthetic.main.fragment_add_news.view.*
import kotlinx.android.synthetic.main.fragment_add_notification.view.*

enum class FeedAbsorber {

    NEWS_ABSORBER {

        override val fragmentRes = R.layout.fragment_add_news

        override fun absorb(fragment: View) = News(
            title = fragment.add_news_title.text.toString(),
            content = fragment.add_news_content.text.toString(),
            imageId = R.mipmap.image1
        )
        override fun toString() = "News"
    },

    NOTIFICATION_ABSORBER {

        override val fragmentRes = R.layout.fragment_add_notification

        override fun absorb(fragment: View) = Notification(
            title = fragment.add_notification_title.text.toString(),
            message = fragment.add_notification_message.text.toString(),
            imageId = R.mipmap.image_marcus
        )
        override fun toString() = "Notification"
    };

    abstract fun absorb(fragment: View) : FeedBase
    abstract val fragmentRes: Int

}
