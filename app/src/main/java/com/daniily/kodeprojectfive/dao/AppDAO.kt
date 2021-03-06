package com.daniily.kodeprojectfive.dao

import com.daniily.kodeprojectfive.data.FeedBase

interface AppDAO {

    fun getFeedObjects(): MutableList<FeedBase>

    fun saveFeedObjects(list: List<FeedBase>)

}
