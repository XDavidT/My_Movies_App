package com.android.academy.work_manager

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.academy.bg_service.activity_bgservice

class HardJobWorker (val appContext: Context,workerParams: WorkerParameters)
    : Worker(appContext.applicationContext,workerParams){
    private var isDestroy = false
    override fun doWork(): Result {
        isDestroy = false
        Log.d("David","HardJobWorker -> doWork")
        var i = 0
        while (i <= 100 && !isDestroy) {
            SystemClock.sleep(100)
            val broadcastIntent = Intent(activity_bgservice.PROGRESS_UPDATE_ACTION)
            broadcastIntent.putExtra(activity_bgservice.PROGRESS_VALUE_KEY,i)
            appContext.applicationContext.sendBroadcast(broadcastIntent)
            Log.d("David","HardJobWorker -> doWork: "+i.toString())
            i++
        }
        return if(i==100) {
            Result.success()
        } else{
            Result.failure()
        }
    }

    override fun onStopped() {
        isDestroy = true
        super.onStopped()
    }
}