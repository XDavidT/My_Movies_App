package com.android.academy.async

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.R

class AsyncActivity : AppCompatActivity(), IAsyncTaskEvents {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
    }

    override fun onPreExecute() {

    }

    override fun onPostExecute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProgressUpdate(num: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

