package com.example.socceralmanac.ui.lastMatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socceralmanac.R

class LastMatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.last_match_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LastMatchFragment.newInstance())
                .commitNow()
        }
    }

}
