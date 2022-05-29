package com.chichi289.fcm.api

import com.chichi289.fcm.models.pushRequest.FcmRequest
import com.chichi289.fcm.models.pushResponse.FcmResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FcmService {

    /* Sample Request
    {
    "to": "DEVICE_FCM_TOKEN",
    "notification": {
        "body": "Body of Your Notification",
        "title": "Title of Your Notification"
    },
    "data": {
        "body": "Notification Body",
        "title": "Notification Title",
        "key_1": "Value for key_1",
        "key_2": "Value for key_2"
    }
}
* */
    @Headers("Content-Type:application/json")
    @POST("fcm/send")
    fun sendPush(
        @Header("Authorization") auth: String,
        @Body fcmRequest: FcmRequest
    ): Call<FcmResponse>
}