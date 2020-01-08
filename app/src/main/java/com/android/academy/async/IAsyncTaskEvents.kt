package com.android.academy.async

interface IAsyncTaskEvents{
    fun onPreExecute()
    fun onPostExecute()
    fun onProgressUpdate(num: Int)
    fun onCancel()

}