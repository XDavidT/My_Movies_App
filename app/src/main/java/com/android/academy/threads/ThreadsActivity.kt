package com.android.academy.threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.R
import kotlinx.android.synthetic.main.activity_threads.*

class ThreadsActivity : AppCompatActivity(), IAsyncTaskEvents {
    private lateinit var newTask: MySimpleAsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threads)
        thread_start.isEnabled = false

        thread_create.setOnClickListener{
            onCreate()
            thread_start.isEnabled = true
        }
        thread_start.setOnClickListener{
            onClickStart()
        }
        thread_cancel.setOnClickListener{
            onCancel()
        }
    }

    override fun onPreExecute() {
        thread_start.isEnabled = false
        thread_create.isEnabled = false
    }

    override fun onPostExecute(isFinish: Boolean) {
        if(isFinish){
            thread_textView.text = "Done !"
        }else{
            thread_textView.text = "Canceled !"

        }
        thread_create.isEnabled = true
    }

    override fun onProgressUpdate(num: Int) {
        thread_textView.text = num.toString()
    }

    override fun onCancel() {
        newTask.cancel()
    }

    fun onCreate(){
        newTask = MySimpleAsyncTask(this)
    }
    fun onClickStart(){
        newTask.excute()
    }
}
