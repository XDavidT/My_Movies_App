package com.android.academy.async

import android.os.AsyncTask

class CounterAsyncTask() : AsyncTask<Void, Void, Boolean>(){
    override fun doInBackground(vararg params: Void?): Boolean {
        var currentNum:Int
        for(i in 1..10){
            currentNum = i
        }
        return true
    }

}