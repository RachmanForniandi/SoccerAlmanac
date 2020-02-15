package com.example.socceralmanac.ui.detailmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.socceralmanac.R
import com.example.socceralmanac.ui.lastmatch.LastMatchFragment

class MatchDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_detail_match, MatchDetailFragment.newInstance())
                .commitNow()
        }
    }
}
