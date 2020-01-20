package com.android.academy.bg_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.academy.R
import kotlinx.android.synthetic.main.activity_bgservice.*

class activity_bgservice : AppCompatActivity() {
    companion object {
        const val PROGRESS_UPDATE_ACTION: String = "PROGRESS_UPDATE_ACTION"
        const val PROGRESS_VALUE_KEY: String = "PROGRESS_VALUE_KEY"
        const val SERVICE_STATUS: String = "SERVICE_STATUS"
    }


    private var backgroundProgressReceiver: BackgroundProgressReceiver? = null

    private fun subscribeForProgressUpdates(){
        if(backgroundProgressReceiver == null) {
            backgroundProgressReceiver = BackgroundProgressReceiver()
            Log.d("David","subscribeForProgressUpdates")
        }
        val progressUpdateActionFilter = IntentFilter(PROGRESS_UPDATE_ACTION)
        registerReceiver(backgroundProgressReceiver,progressUpdateActionFilter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bgservice)

        bgs_progress_status_text.text = "0%"
        bgs_start_srvc.setOnClickListener { startService()  }
        bgs_start_ints.setOnClickListener { startIntsService()  }
    }

    override fun onResume() {
        Log.d("David","onResume")
        subscribeForProgressUpdates()
        super.onResume()
    }

    override fun onPause() {
        backgroundProgressReceiver?.let{
            unregisterReceiver(backgroundProgressReceiver)
        }
        super.onPause()
    }

    

    fun startService(){

    }

    fun startIntsService(){
        Log.d("David","startIntsService")
        startService(Intent(this,HardJobIntentService::class.java))
    }

    inner class BackgroundProgressReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            val progress = intent.getIntExtra(PROGRESS_VALUE_KEY,-1)
            bgs_progress_status_text.text = progress.toString()
        }
    }
}
