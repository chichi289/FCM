package com.chichi289.fcm.models.pushRequest

import com.google.gson.annotations.SerializedName

data class FcmRequest(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("notification")
    val notification: Notification?,
    @SerializedName("to")
    val to: String?
)