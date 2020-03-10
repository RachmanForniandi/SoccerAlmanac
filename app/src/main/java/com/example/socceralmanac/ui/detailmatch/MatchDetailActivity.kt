package com.example.socceralmanac.ui.detailMatch

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.socceralmanac.R
import com.example.socceralmanac.models.search.EventItem

class MatchDetailActivity : AppCompatActivity() {

    companion object{
        var items: EventItem? = null
        fun getDetailSearchOfMatch(caller: Activity, item:EventItem){
            val intentFromSearch =Intent(caller, MatchDetailActivity::class.java)
            caller.startActivity(intentFromSearch)

            items = item


        }
    }

    var deliverBundle: useBundleSearch?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.match_detail)

        Log.e("debugGetResulSearch", ""+items)
        Log.e("debugGetResulSearch1", ""+items?.strSport)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()

                .replace(R.id.container, MatchDetailFragment.newInstance())
                .commitNow()
        }

        val handler = Handler()
        handler.postDelayed({
            items?.let { deliverBundle?.sendSearchResultData(it) }
        }, 3000)
    }

    interface useBundleSearch{
        fun sendSearchResultData(
            item: EventItem
        )
    }

}
