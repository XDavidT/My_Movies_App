package com.android.academy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        subscribeForProgressUpdates()
        super.onResume()
    }

    fun startService(){

    }

    fun startIntsService(){

    }

    inner class BackgroundProgressReceiver : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            val progress = intent.getIntExtra(PROGRESS_VALUE_KEY,-1)

        }
    }
}
