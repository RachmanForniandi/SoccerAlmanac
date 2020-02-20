package com.example.socceralmanac.ui.main

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentTransaction
import com.example.socceralmanac.R
import com.example.socceralmanac.ui.lastmatch.LastMatchFragment
import com.example.socceralmanac.ui.nextmatch.NextMatchFragment
import com.example.socceralmanac.ui.searchmatch.SearchMatchActivity
import com.example.socceralmanac.ui.searchmatch.SearchMatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_menu_search.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportActionBar?.elevation = 0.0f

        val adapter = TabAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        viewPagerMain.adapter = adapter

        ivSearch?.onClick {
            startActivity<SearchMatchActivity>()
        }

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
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search,menu)
        //menu?.findItem(R.id.action_search)?.title = getString(R.string.match_search)
        val manager = getSystemService(Context.SEARCH_SERVICE)as SearchManager
        val searchItem = menu?.findItem(R.id.action_search_view)

        val searchView = searchItem?.actionView as SearchView
        /*searchView?.onClick {
            startActivity<SearchMatchActivity>()
        }*/
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchItem.collapseActionView()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })
        return true
    }
}
