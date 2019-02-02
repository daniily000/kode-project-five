package com.daniily.kodeprojectfive.dao

import android.content.Context
import com.daniily.kodeprojectfive.data.FeedBase
import com.daniily.kodeprojectfive.data.News
import com.daniily.kodeprojectfive.data.Notification
import org.json.JSONArray
import org.json.JSONObject

private const val STORAGE_NAME = "app_data"
private const val FEED_LIST = "feed"
private const val CLASS = "class"

private const val CLASS_NEWS = "News"
private const val NEWS_TITLE = "title"
private const val NEWS_CONTENT = "content"
private const val NEWS_IMAGE_ID = "imageId"

private const val CLASS_NOTIFICATION = "Notification"
private const val NOTIFICATION_TITLE = "title"
private const val NOTIFICATION_MESSAGE = "message"
private const val NOTIFICATION_IMAGE_ID = "imageId"

private const val EMPTY_JSON_ARRAY = "[]";

class AppPrefsDAO(context: Context) : AppDAO {

    private val storage = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)

    override fun getFeedObjects(): MutableList<FeedBase> =
        jsonToFeedObjects(
            JSONArray(storage.getString(FEED_LIST, EMPTY_JSON_ARRAY))
        )

    override fun saveFeedObjects(list: List<FeedBase>) {
        storage.edit().putString(FEED_LIST, feedObjectsToJson(list).toString()).apply()
    }

    private fun feedObjectsToJson(feed: List<FeedBase>) : JSONArray{

        val json = JSONArray()

        feed.forEach {
            feedObjectToJson(it)?.apply { json.put(this) }
        }
        return json
    }

    private fun feedObjectToJson(o: FeedBase) : JSONObject? {

        val json = JSONObject()

        when (o) {

            is News-> {

                json.put(CLASS, CLASS_NEWS)
                json.put(NEWS_TITLE, o.title)
                json.put(NEWS_CONTENT, o.content)
                json.put(NEWS_IMAGE_ID, o.imageId)
            }

            is Notification -> {

                json.put(CLASS, CLASS_NOTIFICATION)
                json.put(NOTIFICATION_TITLE, o.title)
                json.put(NOTIFICATION_MESSAGE, o.message)
                json.put(NOTIFICATION_IMAGE_ID, o.imageId)
            }
        }

        return if (json.has(CLASS))
            json
        else
            null
    }

    fun jsonToFeedObjects(json: JSONArray): MutableList<FeedBase> {

        val list = ArrayList<FeedBase>()

        for (i in 0.until(json.length()) ) {
            jsonToFeedObject(json.getJSONObject(i))?.apply { list.add(this) }
        }

        return list
    }

    fun jsonToFeedObject(json: JSONObject): FeedBase? =

        when (json.getString(CLASS)) {

            CLASS_NEWS -> News(
                json.getString(NEWS_TITLE),
                json.getString(NEWS_CONTENT),
                json.getInt(NEWS_IMAGE_ID))

            CLASS_NOTIFICATION -> Notification(
                json.getString(NOTIFICATION_TITLE),
                json.getString(NOTIFICATION_MESSAGE),
                json.getInt(NOTIFICATION_IMAGE_ID)
            )

            else -> null
        }



}
