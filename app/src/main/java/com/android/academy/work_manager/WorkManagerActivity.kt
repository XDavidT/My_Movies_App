package com.android.academy.work_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.android.academy.R
import kotlinx.android.synthetic.main.activity_work_manager.*
import java.util.*

class WorkManagerActivity : AppCompatActivity() {
    private var lastWorkId: UUID? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        work_manager_enqueue.setOnClickListener{ enqueueWork() }
        work_manager_cancel_work.setOnClickListener { cancelWork() }
    }

    fun enqueueWork(){
        Log.d("David","WorkManagerActivity -> enqueueWork")

        val network = if(work_switch_network.isChecked){
            NetworkType.CONNECTED
        } else{
            NetworkType.NOT_REQUIRED
        }
        val charge = work_switch_charge.isChecked
        val battery = work_switch_battery_not_low.isChecked

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(network)
            .setRequiresCharging(charge)
            .setRequiresBatteryNotLow(battery)
            .build()

        val hardJobRequest = OneTimeWorkRequest
            .Builder(HardJobWorker::class.java)
            .setConstraints(constraints)
            .build()
        lastWorkId = hardJobRequest.id
        Log.d("David","WorkManagerActivity -> enqueueWork\n" +
                "Started with UUID: "+lastWorkId.toString())
        WorkManager.getInstance(this).enqueue(hardJobRequest)

    }

    fun cancelWork(){
        Log.d("David","WorkManagerActivity -> cancelWork")
        lastWorkId?.let {
            Log.d("David","WorkManagerActivity -> cancelWork->"+lastWorkId.toString())
            WorkManager.getInstance(this).cancelWorkById(it)
        }
    }
}
