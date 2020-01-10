package com.android.academy.async

import android.os.AsyncTask
import kotlin.concurrent.thread

class CounterAsyncTask(
    val iAsyncTaskEvents: IAsyncTaskEvents
) : AsyncTask<Void, Int, Boolean>(){

    override fun onPreExecute() {
        iAsyncTaskEvents.onPreExecute()
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        for(i in 1..20){
            Thread.sleep(1000)
            publishProgress(i)
        }
        return true
    }

    override fun onProgressUpdate(vararg values: Int?) {
        values[0]?.let {
            iAsyncTaskEvents.onProgressUpdate(it)
        }
    }

    override fun onPostExecute(result: Boolean?) {
        result?.let {
            iAsyncTaskEvents.onPostExecute(it)

        }
    }

    override fun onCancelled() {
        iAsyncTaskEvents.onCancel()
    }
}