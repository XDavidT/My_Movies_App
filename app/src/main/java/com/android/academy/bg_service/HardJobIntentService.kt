package com.android.academy.bg_service

import android.app.IntentService
import android.content.Intent
import android.os.SystemClock
import android.widget.Toast

const val SERVICE_NAME = "HardJobIntentService"
class HardJobIntentService : IntentService(SERVICE_NAME) {
    private var isDestroyed = false
    override fun onHandleIntent(intent: Intent?) {
        isDestroyed = false
        Toast.makeText(this,"Started !",Toast.LENGTH_LONG).show()
        var i = 0
        while (i <= 100 && !isDestroyed) {
            SystemClock.sleep(100)
            val broadcastIntent = Intent(activity_bgservice.PROGRESS_UPDATE_ACTION)
            broadcastIntent.putExtra(activity_bgservice.PROGRESS_VALUE_KEY,i)
            sendBroadcast(broadcastIntent)
            i++
        }
    }

    override fun onDestroy() {
        isDestroyed = true
        super.onDestroy()
    }

}