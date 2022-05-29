package com.chichi289.fcm.models.pushRequest

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("body")
    val body: String?,
    @SerializedName("title")
    val title: String?
)