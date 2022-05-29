package com.chichi289.fcm.models.pushRequest

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("body")
    val body: String?,
    @SerializedName("key_1")
    val key1: String?,
    @SerializedName("key_2")
    val key2: String?,
    @SerializedName("title")
    val title: String?
)