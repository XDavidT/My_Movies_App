package com.android.academy.async

import android.os.AsyncTask
import kotlin.concurrent.thread

class CounterAsyncTask(
    val iAsyncTaskEvents: IAsyncTaskEvents,
    var currentNumber: Int
) : AsyncTask<Void, Int, Boolean>(){

    override fun onPreExecute() {
        iAsyncTaskEvents.onPreExecute()
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        while(currentNumber<20){
            currentNumber = currentNumber.inc()
            Thread.sleep(1000)
            publishProgress(currentNumber)
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