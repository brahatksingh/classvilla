package com.brewingkotlin.classvilla

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val logo: VideoView = findViewById(R.id.SignLogo)
        val logouri: Uri = Uri.parse("android.resource://$packageName/${R.raw.signlogo}")
        logo.setVideoURI(logouri)
        logo.start()
    }
}