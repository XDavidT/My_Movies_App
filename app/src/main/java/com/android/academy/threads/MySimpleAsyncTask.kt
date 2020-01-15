package com.android.academy.threads

import android.os.Handler
import android.os.Looper

const val THREAD_NAME = "CustomThread"

class MySimpleAsyncTask(
    private val iAsyncTaskEvents: IAsyncTaskEvents
){
    private var customThread: Thread? = null
    private var isCanceld:Boolean = false

    fun onPreExecute(){
        runOnUiThread(Runnable {
            iAsyncTaskEvents.onPreExecute()
        })
    }

    fun excute(){
        runOnUiThread(Runnable {
            onPreExecute()
            customThread = Thread({
                val isFinish = doInBackground()
                runOnUiThread(
                    Runnable {
                        onPostExecute(isFinish)
                    }
                )
            }, THREAD_NAME).also {
                it.start()
            }
        })
    }

    fun doInBackground():Boolean{
        for (i in 1..10){
            if(isCanceld) {
                return false
            }
            onProgressUpdate(i)
            Thread.sleep(1000)
        }
        return true

    }

    fun onProgressUpdate(num: Int) {
        runOnUiThread(Runnable{
            iAsyncTaskEvents.onProgressUpdate(num)
        })

    }

    fun onPostExecute(boolean: Boolean){
        runOnUiThread(Runnable{
            iAsyncTaskEvents.onPostExecute(boolean)
        })
    }

    fun cancel(){
        isCanceld = true
        Thread.sleep(1000)
        customThread?.interrupt()
    }

    fun runOnUiThread(runnable: Runnable){
        Handler(Looper.getMainLooper()).post(runnable)
    }
}