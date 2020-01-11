package com.android.academy.async

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.academy.R
import kotlinx.android.synthetic.main.activity_async.*

class AsyncActivity : AppCompatActivity(), IAsyncTaskEvents {
    private lateinit var newCounterTask: CounterAsyncTask
    private lateinit var currentNumber: Number
    private lateinit var currentStatus: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        if(savedInstanceState==null){
            currentNumber = 0
        }

        //Create Task
        async_create.setOnClickListener {
            createTask()
        }

        //Start Task
        async_start.setOnClickListener {
            startTask()
        }

        //Cancel Task
        async_cancel.setOnClickListener{
            stopTask()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("status",currentStatus)
        outState.putInt("currNum",currentNumber.toInt())

        Log.d("David","onSaveInstanceState")
        Log.d("David","onSaveInstanceState-> status: "+currentStatus +
        " Current number: "+currentNumber)
        stopTask()
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.getString("status")?.let{
            currentStatus = it
        }
        currentNumber = savedInstanceState.getInt("currNum",0)

        Log.d("David","onRestoreInstanceState - status: "+currentStatus+
        " number is: "+currentNumber)


        if(currentStatus == "create")
            createTask()
        else if(currentStatus == "start"){
            createTask()
            startTask()
        }

        super.onRestoreInstanceState(savedInstanceState)
        Log.d("David","onRestoreInstanceState")
    }

    override fun onPreExecute() {

    }

    override fun onProgressUpdate(num: Int) {
        async_textView.text = num.toString()
        currentNumber = num
    }

    override fun onPostExecute(boolean: Boolean) {
        boolean.let {
            async_textView.text = "DONE"
        }
    }

    override fun onCancel() {
        async_textView.text = "Ready !"
    }

    fun createTask(){
        currentStatus = "create"
        Log.d("David","Status: "+currentStatus)
        newCounterTask = CounterAsyncTask(this,currentNumber.toInt())
    }
    fun startTask(){
        currentStatus = "start"
        Log.d("David","Status: "+currentStatus)
        newCounterTask.execute()
    }

    fun stopTask(){
        currentStatus = "stop"
        newCounterTask.cancel(true)
    }
}

