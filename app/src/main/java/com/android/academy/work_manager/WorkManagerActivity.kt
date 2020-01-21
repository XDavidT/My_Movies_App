package com.android.academy.work_manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.android.academy.R
import com.android.academy.bg_service.activity_bgservice
import kotlinx.android.synthetic.main.activity_bgservice.*
import kotlinx.android.synthetic.main.activity_work_manager.*
import java.util.*

class WorkManagerActivity : AppCompatActivity() {
    companion object {
        const val PROGRESS_UPDATE_ACTION: String = "PROGRESS_UPDATE_ACTION"
        const val PROGRESS_VALUE_KEY: String = "PROGRESS_VALUE_KEY"
        const val SERVICE_STATUS: String = "SERVICE_STATUS"
    }
    private var lastWorkId: UUID? = null
    private var backgroundProgressReceiver: BackgroundProgressReceiver? = null

    private fun subscribeForProgressUpdates(){
        if(backgroundProgressReceiver == null) {
            backgroundProgressReceiver = BackgroundProgressReceiver()
            Log.d("David","subscribeForProgressUpdates")
        }
        val progressUpdateActionFilter = IntentFilter(PROGRESS_UPDATE_ACTION)
        registerReceiver(backgroundProgressReceiver,progressUpdateActionFilter)
    }
    private fun unsubscribeForProgressUpdates(){
        unregisterReceiver(backgroundProgressReceiver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        work_manager_enqueue.setOnClickListener{ enqueueWork() }
        work_manager_cancel_work.setOnClickListener { cancelWork() }
    }

    override fun onStart() {
        super.onStart()
        subscribeForProgressUpdates()
    }

    override fun onStop() {
        unsubscribeForProgressUpdates()
        super.onStop()
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

    inner class BackgroundProgressReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            val progress = intent.getIntExtra(PROGRESS_VALUE_KEY,-1)
            if(progress>0) {
                work_manager_process.text = progress.toString()
            }else{
                Log.d("David","onReceive wrong")
            }
        }
    }
}
