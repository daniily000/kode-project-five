package com.daniily.kodeprojectfive.data

data class Notification(
    val title: String,
    val message: String,
    val imageId: Int
): FeedBase()
