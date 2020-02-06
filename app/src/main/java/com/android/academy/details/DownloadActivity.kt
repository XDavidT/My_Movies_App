package com.android.academy.details

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.academy.R
import com.android.academy.movie_data.MovieModel

class DownloadActivity : AppCompatActivity() {

    companion object{
        const val PERMISSIONS_REQUEST_CODE: Int = 42
        const val PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
        const val detailsKey = "ARG_MOVIE_MODEL"
        fun startActivity(context: Context,movieModel: MovieModel){
            val intent = Intent(context,DownloadActivity::class.java)
            intent.putExtra(detailsKey,movieModel)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        val movieModel = intent.getParcelableExtra<MovieModel>(detailsKey)
        if(isPermissionGranted){

        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(PERMISSION),
                PERMISSIONS_REQUEST_CODE)
        }
    }

    private val isPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(
            this,
            PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

}
