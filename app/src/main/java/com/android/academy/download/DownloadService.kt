package com.android.academy.download

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.android.academy.R

class DownloadService : Service() {
    lateinit var movieUrl: String

    companion object{
        const val ONGOING_NOTIFICATION_ID: Int = 14000605
        const val URL: String = "URL"
        const val CHANNEL_ID:String = "123"
        fun startService(activity: Activity,url:String){
            val intent = Intent(activity, DownloadService::class.java)
            intent.putExtra(URL,url)
            activity.startService(intent)
        }
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra(URL)?.let {
            movieUrl = it
        }
        startForgService()
        return START_REDELIVER_INTENT
    }

    private fun startForgService(){
        createNotificationChannel()
        startForeground(ONGOING_NOTIFICATION_ID,createNotification(0))
        DownloadThread(movieUrl,object: DownloadThread.DownloadCallback{
            override fun onProgressUpdate(percent: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDownloadFinish(filePath: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(error: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }).start()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun createNotification(progress:Int):Notification{
        val notificationIntent = Intent(this,DownloadActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)
        val progressStr = getString(R.string.notification_message,progress)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getText(R.string.notification_title))
            .setContentText(progressStr)
            .setSmallIcon(R.drawable.ic_my_icon)
            .setProgress(100,progress,false)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun createNotificationChannel(){
        val name = getString(R.string.channel_name)
        val description = getString(R.string.channel_desc)
        val importance = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationManager.IMPORTANCE_HIGH
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val mChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(CHANNEL_ID,name,importance)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        mChannel.description = description
        mChannel.enableLights(true)
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }
}