package com.chichi289.fcm.models.pushResponse

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("message_id")
    val messageId: String?
)