package com.android.academy.download

import javax.security.auth.callback.Callback

class DownloadThread (
    private val imageURL:String,
    private val downloadCallback: Callback
): Thread(){

}