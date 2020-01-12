package com.android.academy.threads

interface IAsyncTaskEvents{
    fun onPreExecute()
    fun onPostExecute(boolean: Boolean)
    fun onProgressUpdate(num: Int)
    fun onCancel()
}