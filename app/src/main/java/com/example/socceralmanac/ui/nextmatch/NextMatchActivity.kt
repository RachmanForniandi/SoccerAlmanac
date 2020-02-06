package com.example.socceralmanac.ui.nextmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.socceralmanac.R
import com.example.socceralmanac.ui.nextmatch.NextMatchFragment

class NextMatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next_match_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NextMatchFragment.newInstance())
                .commitNow()
        }
    }

}
