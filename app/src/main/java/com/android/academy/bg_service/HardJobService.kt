package com.android.academy.bg_service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG

const val HT_NAME = "MAIN THREAD"
class HardJobService : Service(){
    private var serviceHandler: ServiceHandler? = null
    private var isDestroyed = false
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.d("David", "HardJobService->OnCreate")
        val thread = HandlerThread(TAG,Process.THREAD_PRIORITY_BACKGROUND)
        thread.start()

        val serviceLooper = thread.looper
        serviceHandler = ServiceHandler(serviceLooper)
    }

    override fun onDestroy() {
        Log.d("David", "HardJobService->onDestroy")
        isDestroyed = true
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("David", "HardJobService->onStartCommand")
        isDestroyed = false
        serviceHandler?.let {
            val message = it.obtainMessage()
            message.arg1 = startId
            it.sendMessage(message)
        }
        return START_STICKY
    }

    private inner class ServiceHandler(looper: Looper): Handler(looper){
        override fun handleMessage(msg: Message) {
            Log.d("David","HardJobService-> handleMessage")
            var i = 0
            while(i <= 100 && !isDestroyed){
                SystemClock.sleep(100)
                val intent = Intent(activity_bgservice.PROGRESS_UPDATE_ACTION)
                intent.putExtra(activity_bgservice.PROGRESS_VALUE_KEY,i)
                sendBroadcast(intent)
                i++
                Log.d("David","HardJobService-> handleMessage: "+i.toString())
            }
            stopSelf(msg.arg1)
        }
    }
}