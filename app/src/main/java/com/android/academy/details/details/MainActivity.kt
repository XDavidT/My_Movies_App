package com.android.academy.details.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.academy.R
import com.android.academy.fragments.MoviesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moviesFragment = MoviesFragment()

        supportFragmentManager.beginTransaction().
            add(R.id.activity_main_frame,moviesFragment).commit()
    }
}