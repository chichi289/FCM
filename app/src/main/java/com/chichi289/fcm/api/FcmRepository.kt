package com.chichi289.fcm.api

import androidx.lifecycle.MutableLiveData
import com.chichi289.fcm.models.Resource
import com.chichi289.fcm.models.pushRequest.FcmRequest
import com.chichi289.fcm.models.pushResponse.FcmResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FcmRepository(
    private val fcmService: FcmService
) {
    fun sendPush(
        fcmServerKey: String,
        fcmRequest: FcmRequest
    ): MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        val call = fcmService.sendPush(
            auth = "key=$fcmServerKey",
            fcmRequest = fcmRequest
        )
        call.enqueue(object : Callback<FcmResponse> {
            override fun onResponse(call: Call<FcmResponse>, response: Response<FcmResponse>) {
                val mBean = response.body()
                if (response.isSuccessful && mBean != null) {
                    data.value = Resource.Success(mBean)
                } else {
                    data.value = Resource.Error<String>(response.errorBody()?.string() ?: "Error")
                }
            }

            override fun onFailure(call: Call<FcmResponse>, t: Throwable) {
                data.value = Resource.Error<String>(t.message ?: "onFailure")
            }

        })
        return data
    }
}