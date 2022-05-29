package com.chichi289.fcm

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chichi289.fcm.models.Resource
import com.chichi289.fcm.models.pushRequest.Data
import com.chichi289.fcm.models.pushRequest.FcmRequest
import com.chichi289.fcm.models.pushRequest.Notification
import com.chichi289.fcm.models.pushResponse.FcmResponse
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val edtFcmServerKey: TextInputEditText by lazy {
        findViewById(R.id.edtFcmServerKey)
    }
    private val edtDeviceToken: TextInputEditText by lazy {
        findViewById(R.id.edtDeviceToken)
    }
    private val btnSend: MaterialButton by lazy {
        findViewById(R.id.btnSend)
    }
    private val progressBar: ProgressBar by lazy {
        findViewById(R.id.progressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.sendPushLiveData.observe(this) {
            /* Sample response
            {
                "multicast_id": 628433448782943792,
                "success": 1,
                "failure": 0,
                "canonical_ids": 0,
                "results": [
                    {
                        "message_id": "0:1653537016733145%b27392e3b27392e3"
                    }
                ]
            }
            * */
            showLoading(false)
            when (it) {
                is Resource.Success<*> -> {
                    val response = it.data as FcmResponse
                    if (response.success == 1) {
                        toast("Push sent successfully.")
                    }
                }

                is Resource.Error<*> -> {
                    toast("${it.message}")
                }
            }
        }

        btnSend.setOnClickListener {
            if (isValidated()) {
                showLoading(true)
                val data = Data(
                    title = "data title",
                    body = "data body",
                    key1 = "value1",
                    key2 = "value2"
                )
                val notification = Notification(
                    title = "notification title",
                    body = "notification body"
                )
                val fcmRequest = FcmRequest(
                    data = data,
                    notification = notification,
                    to = edtDeviceToken.text.toString()
                )
                viewModel.sendPush(
                    edtFcmServerKey.text.toString(),
                    fcmRequest
                )
            }
        }
    }

    private fun isValidated(): Boolean {
        val fcmServerKey = edtFcmServerKey.text.toString().trim()
        val deviceToken = edtDeviceToken.text.toString().trim()

        if (fcmServerKey.isEmpty()) {
            toast("Please enter fcm server key.")
            return false
        }

        if (deviceToken.isEmpty()) {
            toast("Please enter device token.")
            return false
        }

        return true

    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(b: Boolean) {
        if (b) {
            progressBar.visibility = View.VISIBLE
            btnSend.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            btnSend.visibility = View.VISIBLE
        }
    }

}