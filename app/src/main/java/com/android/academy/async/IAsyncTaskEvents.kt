package com.android.academy.async

interface IAsyncTaskEvents{
    fun onPreExecute()
    fun onPostExecute(boolean: Boolean)
    fun onProgressUpdate(num: Int)
    fun onCancel()
}