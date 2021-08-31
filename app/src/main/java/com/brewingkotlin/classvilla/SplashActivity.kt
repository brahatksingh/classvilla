package com.brewingkotlin.classvilla

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.brewingkotlin.classvilla.Activities.SignInActivity

@Suppress("DEPRECATION")

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val splashvv : VideoView = findViewById(R.id.splashvv)
        val offlineuri : Uri = Uri.parse("android.resource://$packageName/${R.raw.splashvid}")
        splashvv.setVideoURI(offlineuri)
        splashvv.requestFocus()
        splashvv.start()

        Handler().postDelayed({
            val mainIntent = Intent(this, SignInActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 2500)
    }
}
