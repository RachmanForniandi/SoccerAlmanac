package com.example.socceralmanac.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.example.socceralmanac.R
import com.example.socceralmanac.adapters.TabAdapter
import com.example.socceralmanac.ui.lastMatch.LastMatchFragment
import com.example.socceralmanac.ui.nextMatch.NextMatchFragment
import com.example.socceralmanac.ui.searchMatch.SearchMatchActivity
import kotlinx.android.synthetic.main.fragment_match_section.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class MatchSectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_section, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        with(activity as AppCompatActivity){
            setSupportActionBar(toolbar_match)
            setTitle(getString(R.string.soccer_matches))
            val adapter = TabAdapter(
                childFragmentManager, mapOf(
                    getString(R.string.last_match) to LastMatchFragment(),
                    getString(R.string.next_match) to NextMatchFragment()
                ))
            viewPagerMain.adapter = adapter

            tabLayoutMain.setupWithViewPager(viewPagerMain)
        }

    }

    /*class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val tabName: Array<String> = arrayOf("Last Match", "Next Match")

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> LastMatchFragment()
            1 -> NextMatchFragment()
            else -> LastMatchFragment()

        }

        override fun getCount(): Int = 2
        override fun getPageTitle(position: Int): CharSequence? = tabName[position]

    }*/

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.action_search)?.title = getString(R.string.match_search)
        return super.onCreateOptionsMenu(menu,menuInflater)
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

}
