package com.example.socceralmanac.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.example.socceralmanac.R
import com.example.socceralmanac.ui.lastMatch.LastMatchFragment
import com.example.socceralmanac.ui.nextMatch.NextMatchFragment
import com.example.socceralmanac.ui.searchMatch.SearchMatchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var contentWhole: FrameLayout? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            bottom_nav_options.selectedItemId -> return@OnNavigationItemSelectedListener false
            R.id.menu_matches ->{
                val fragment = MatchSectionFragment()
                addSectionFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_teams ->{
                val fragment = TeamSectionFragment()
                addSectionFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_favorites ->{
                val fragment = FavoritedSectionFragment()
                addSectionFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav_options.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val defaultFragment = MatchSectionFragment()
        addSectionFragment(defaultFragment)
        //supportActionBar?.elevation = 0.0f

        /*val adapter = TabAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        viewPagerMain.adapter = adapter

        tabLayoutMain.setupWithViewPager(viewPagerMain)*/

    }

    /*class TabAdapter(sfm: FragmentManager, behavior: Int) :
        FragmentStatePagerAdapter(sfm, behavior) {

        private val tabName: Array<String> = arrayOf("Last Match", "Next Match")

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> LastMatchFragment()
            1 -> NextMatchFragment()
            else -> LastMatchFragment()

        }

        override fun getCount(): Int = 2
        override fun getPageTitle(position: Int): CharSequence? = tabName[position]

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.action_search).title = getString(R.string.match_search)
        return super.onCreateOptionsMenu(menu)
    }


    }*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.action_search)?.title = getString(R.string.match_search)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                startActivity<SearchMatchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addSectionFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.main_container,fragment,fragment.javaClass.simpleName)
            .commit()

    }

}
