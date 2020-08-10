package com.androfara.bwamovapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.androfara.bwamovapp.onboarding.OnboardingOneActivity

/*
    Ini adalah activity pertama yang di run
    Tidak ada fitur spesial disini
    Hanya melekukan pending beberapa detik saja
 */

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this@SplashScreenActivity, OnboardingOneActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}