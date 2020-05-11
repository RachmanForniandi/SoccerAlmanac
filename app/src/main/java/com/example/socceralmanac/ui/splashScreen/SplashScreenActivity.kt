package com.example.socceralmanac.ui.splashScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socceralmanac.R
import com.example.socceralmanac.ui.main.MainActivity
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Timer("splashGone", true).schedule(3000) {
            startActivity<MainActivity>()
            finish()
        }
    }
}
