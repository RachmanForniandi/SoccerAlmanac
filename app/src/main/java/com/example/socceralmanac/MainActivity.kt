package com.example.socceralmanac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.PagerAdapter
import com.example.socceralmanac.ui.lastmatch.LastMatchFragment
import com.example.socceralmanac.ui.nextmatch.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 0.0f

        val adapter = TabAdapter(supportFragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPagerMain.adapter = adapter

        tabLayoutMain.setupWithViewPager(viewPagerMain)
    }

    class TabAdapter(sfm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(sfm,behavior) {

        private val tabName : Array<String> = arrayOf("Last Match","Next Match" )

        override fun getItem(position: Int): Fragment = when(position) {
            0 -> LastMatchFragment()
            1 -> NextMatchFragment()
            else -> LastMatchFragment()

        }
        override fun getCount(): Int =2
        override fun getPageTitle(position: Int): CharSequence? = tabName.get(position)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        return super.onCreateOptionsMenu(menu)
    }

}
