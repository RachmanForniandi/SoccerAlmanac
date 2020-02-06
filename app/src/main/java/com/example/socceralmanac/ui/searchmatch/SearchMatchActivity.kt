package com.example.socceralmanac.ui.searchmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.socceralmanac.R

class SearchMatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_match_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchMatchFragment.newInstance())
                .commitNow()
        }
    }

}
