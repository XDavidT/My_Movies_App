package com.android.academy.async

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.academy.R
import kotlinx.android.synthetic.main.activity_async.*

class AsyncActivity : AppCompatActivity(), IAsyncTaskEvents {
    private lateinit var newCounterTask: CounterAsyncTask
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
        async_create.setOnClickListener {

            newCounterTask = CounterAsyncTask(this)
            Log.d("David",newCounterTask.toString())
        }
        async_start.setOnClickListener {
            newCounterTask.execute()
        }
        async_cancel.setOnClickListener{
            newCounterTask.cancel(true)

        }

    }

    override fun onPreExecute() {

    }

    override fun onProgressUpdate(num: Int) {
        async_textView.text = num.toString()
    }

    override fun onPostExecute(boolean: Boolean) {
        boolean.let {
            async_textView.text = "DONE"
        }
    }

    override fun onCancel() {
        async_textView.text = "Ready !"
    }
}

