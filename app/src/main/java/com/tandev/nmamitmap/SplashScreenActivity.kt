package com.tandev.nmamitmap

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.tandev.nmamitmap.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logo: ImageView = findViewById(R.id.iv_splash_logo)
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logo.startAnimation(anim)

        Handler().postDelayed({
            startActivity(Intent(this, MapsActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            finish()
        }, 1250)
    }
}