package com.android.academy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun moveToYoutubeLink(view : View){
        val move_to_link = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6ZfuNTqbHE8"))
        move_to_link.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        move_to_link.setPackage("com.google.android.youtube")
        startActivity(move_to_link)

    }
}
