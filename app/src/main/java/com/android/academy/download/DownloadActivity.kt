package com.android.academy.download

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.academy.R
import com.android.academy.movie_data.MovieModel

class DownloadActivity : AppCompatActivity() {
    lateinit var movieModel: MovieModel
    companion object{
        const val PERMISSIONS_REQUEST_CODE: Int = 42
        const val PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
        const val detailsKey = "ARG_MOVIE_MODEL"
        fun startActivity(context: Context,movieModel: MovieModel){
            val intent = Intent(context,
                DownloadActivity::class.java)
            intent.putExtra(detailsKey,movieModel)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        movieModel = intent.getParcelableExtra<MovieModel>(detailsKey)!!
        if(isPermissionGranted){
            Log.d("David", "DownloadActivity->isPermissionGranted->True")
            startDownloadService()
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Permission needed")
            builder.setMessage("To download this Image you must provide " +
                    "access to use device storage. Please allow to continue or cancel to dismiss")


            builder.setPositiveButton("OK"){dialog, which ->
                ActivityCompat.requestPermissions(
                this,
                arrayOf(PERMISSION),
                    PERMISSIONS_REQUEST_CODE
                ) }
            builder.setNegativeButton("Cancel"){dialog, which->
                Log.d("David","Permission - user ask to cancel")
                finish()
            }
            builder.create().show()
        }
    }

    private val isPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(
            this,
            PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    //Only after system pop-up
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERMISSIONS_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("David","onRequestPermissionsResult -> we have permission")
                startDownloadService()
            }
            else{
                Log.d("David","onRequestPermissionsResult -> we have't permission")
                finish()
            }
        }
    }

    private fun startDownloadService(){
        DownloadService.startService(this,movieModel.trailerUrl)
    }
}
