package com.example.socceralmanac.ui.nextMatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socceralmanac.R

class NextMatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next_match_activity)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NextMatchFragment.newInstance())
                .commitNow()
        }*/
    }

}
