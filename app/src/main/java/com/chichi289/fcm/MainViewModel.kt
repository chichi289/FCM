package com.chichi289.fcm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.chichi289.fcm.api.ApiModule
import com.chichi289.fcm.api.FcmRepository
import com.chichi289.fcm.models.pushRequest.FcmRequest

class MainViewModel : ViewModel() {

    private val fcmRepository: FcmRepository by lazy {
        ApiModule.provideFcmRepository()
    }

    val sendPushLiveData: MediatorLiveData<Any> by lazy { MediatorLiveData() }
    fun sendPush(fcmServerKey: String, fcmRequest: FcmRequest) {
        sendPushLiveData.addSource(
            fcmRepository.sendPush(fcmServerKey, fcmRequest)
        ) {
            sendPushLiveData.value = it
        }
    }

}