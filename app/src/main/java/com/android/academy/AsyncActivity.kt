package com.android.academy

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AsyncActivity : AppCompatActivity() {
    companion object{
        class CounterAsyncTask() : AsyncTask<Void,Void,Boolean>(){
            override fun doInBackground(vararg params: Void?): Boolean {
                var currentNum:Int
                for(i in 1..10){
                    currentNum = i
                }
                return true
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
    }
}

