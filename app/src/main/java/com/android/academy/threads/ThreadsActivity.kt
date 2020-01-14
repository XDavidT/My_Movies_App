package com.android.academy.threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.R

class ThreadsActivity : AppCompatActivity(), IAsyncTaskEvents {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threads)
    }

    override fun onPreExecute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostExecute(boolean: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProgressUpdate(num: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
