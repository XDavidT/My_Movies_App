package com.android.academy.download

import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class DownloadThread (
    private val imageURL:String,
    private val downloadCallback: DownloadCallback
): Thread(){
    private var progress = 0
    private var lastUpdateTime:Long = 0

    override fun run() {
        //Get file to save
        val file = createFile()
        if (file == null){
            downloadCallback.onError("Can't create File !!")
            return
        }

        //Start download
        var connection: HttpURLConnection? = null
        var inputStream:InputStream? = null
        var fos: FileOutputStream? = null

        val url = URL(imageURL)
        connection = url.openConnection() as HttpURLConnection
        connection.connect()

        if(connection.responseCode != HttpURLConnection.HTTP_OK){
            downloadCallback.onError("Server return HTTP error code:"+
            connection.responseCode+" :"+connection.responseMessage)
        }

        val fileLength = connection.contentLength
         fos = FileOutputStream(file.path)

        var next:Int
        val data = ByteArray(1024)
        inputStream?.let{
            while(it.read(data).let{next=it;it!=-1}) {
                fos.write(data, 0, next)
                updateProgress(fos,fileLength)
            }
        }
    }

    @Throws(IOException::class)
    private fun updateProgress(fos:FileOutputStream,fileLength:Int){
        if(lastUpdateTime==0L || System.currentTimeMillis()>lastUpdateTime+500){
            val count = fos.channel.size().toInt() * 100 / fileLength
            if(count > progress){
                progress = count
                lastUpdateTime = System.currentTimeMillis()
                downloadCallback.onProgressUpdate(progress)
            }
        }

    }

    private fun createFile(): File?{
        val mediaStorageDirectory = File(
            MediaStore.VOLUME_EXTERNAL_PRIMARY.toString() + File.separator
        )
        if(!mediaStorageDirectory.exists()){
            if(!mediaStorageDirectory.mkdirs()){
                return null
            }
        }
        val imageName = createImageFileName() +".jpg"
        return File(mediaStorageDirectory.path+File.separator+imageName)
    }

    private fun createImageFileName():String{
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return "FILE_$timestamp"
    }

    interface DownloadCallback{
        fun onProgressUpdate(percent:Int)
        fun onDownloadFinish(filePath:String)
        fun onError(error:String)
    }
}