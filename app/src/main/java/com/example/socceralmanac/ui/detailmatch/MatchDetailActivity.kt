package com.example.socceralmanac.ui.detailmatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socceralmanac.R
import com.example.socceralmanac.ui.detailMatch.MatchDetailFragment

class MatchDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.match_detail)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MatchDetailFragment.newInstance())
                .commitNow()
        }
    }

}
