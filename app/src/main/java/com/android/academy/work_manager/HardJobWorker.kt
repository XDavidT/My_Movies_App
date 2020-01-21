package com.android.academy.work_manager

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.academy.bg_service.activity_bgservice

class HardJobWorker (appContext: Context,workerParams: WorkerParameters)
    : Worker(appContext,workerParams){
    override fun doWork(): Result {
        Log.d("David","HardJobWorker -> doWork")
        var i = 0
        while (i <= 100) {
            SystemClock.sleep(100)

            Log.d("David","HardJobWorker -> doWork: "+i.toString())
            i++
        }
        return if(i==100) {
            Result.success()
        } else{
            Result.failure()
        }
    }
}